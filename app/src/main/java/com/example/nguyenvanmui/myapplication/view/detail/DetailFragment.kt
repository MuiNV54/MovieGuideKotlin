package com.example.nguyenvanmui.myapplication.view.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.nguyenvanmui.myapplication.Constants
import com.example.nguyenvanmui.myapplication.Constants.SITE_YOUTUBE
import com.example.nguyenvanmui.myapplication.Constants.YOUTUBE_THUMBNAIL_URL
import com.example.nguyenvanmui.myapplication.MainApplication
import com.example.nguyenvanmui.myapplication.R
import com.example.nguyenvanmui.myapplication.R.id.video_thumb
import com.example.nguyenvanmui.myapplication.data.remote.entity.Movie
import com.example.nguyenvanmui.myapplication.data.remote.entity.Review
import com.example.nguyenvanmui.myapplication.data.remote.entity.Video
import kotlinx.android.synthetic.main.fragment_movie_details.*
import kotlinx.android.synthetic.main.review.*
import kotlinx.android.synthetic.main.trailers_and_reviews.*
import kotlinx.android.synthetic.main.trailers_and_reviews.view.*
import javax.inject.Inject

/**
 * Created by nguyen.van.mui on 02/02/2018.
 */
class DetailFragment : Fragment(), DetailView, View.OnClickListener {
    @Inject
    lateinit var movieDetailsPresenter: DetailPresenter

    private var movie: Movie? = null

    fun DetailFragment() {
        // Required empty public constructor
    }

    companion object {
        fun getInstance(movie: Movie): DetailFragment {
            val args = Bundle()
            args.putParcelable(Constants.MOVIE, movie)
            val movieDetailsFragment = DetailFragment()
            movieDetailsFragment.arguments = args
            return movieDetailsFragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        (activity?.application as MainApplication).createDetailComponent().inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_movie_details, container, false)
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setToolbar()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments != null) {
            val movie = arguments?.get(Constants.MOVIE) as Movie
            if (movie != null) {
                this.movie = movie
                movieDetailsPresenter.setViewPresenter(this)
                movieDetailsPresenter.showDetails(movie)
                movieDetailsPresenter.showFavoriteButton(movie)
            }
        }

        favorite.setOnClickListener(this)
    }

    private fun setToolbar() {
        collapsing_toolbar.apply {
            setContentScrimColor(
                    ContextCompat.getColor(context, R.color.colorPrimary))
            title = getString(R.string.movie_details)
            setCollapsedTitleTextAppearance(R.style.CollapsedToolbar)
            setExpandedTitleTextAppearance(R.style.ExpandedToolbar)
            isTitleEnabled = true
        }

        if (toolbar != null) {
            (activity as AppCompatActivity).setSupportActionBar(toolbar)

            val actionBar = (activity as AppCompatActivity).supportActionBar
            actionBar?.setDisplayHomeAsUpEnabled(true)
        } else {
            // Don't inflate. Tablet is in landscape mode.
        }
    }

    override fun showDetails(movie: Movie) {
        Glide.with(context).load(movie.backdropPath?.let { Constants.getBackdropPath(it) }).into(
                movie_poster)
        movie_name.setText(movie.title)
        movie_year.text = String.format(getString(R.string.release_date),
                movie.releaseDate)
        movie_rating.text = String.format(getString(R.string.rating),
                movie.voteAverage.toString())
        movie_description.setText(movie.overview)
        movieDetailsPresenter.showTrailers(movie)
        movieDetailsPresenter.showReviews(movie)
    }

    override fun showTrailers(trailers: List<Video>) {
        if (trailers.isEmpty()) {
            trailers_label.visibility = View.GONE
            this.trailers.visibility = View.GONE
            trailers_container.visibility = View.GONE

        } else {
            trailers_label.visibility = View.VISIBLE
            this.trailers.visibility = View.VISIBLE
            trailers_container.visibility = View.VISIBLE

            this.trailers.removeAllViews()
            val inflater = activity?.layoutInflater
            val options = RequestOptions()
                    .placeholder(R.color.colorPrimary)
                    .centerCrop()
                    .override(150, 150)

            for (trailer in trailers) {
                val thumbContainer = inflater?.inflate(R.layout.video, this.trailers, false)
                (video_thumb as ImageView).apply {
                    setTag(trailer.getUrl())
                    requestLayout()
                    setOnClickListener(this@DetailFragment)
                    Glide.with(context)
                            .load(trailer.getThumbnailUrl())
                            .apply(options)
                            .into(this)
                    this.trailers.addView(thumbContainer)
                }

            }
        }
    }

    override fun showReviews(reviews: List<Review>) {
        if (reviews.isEmpty()) {
            this.reviews.visibility = View.GONE
            this.reviews.visibility = View.GONE
        } else {
            this.reviews.visibility = View.VISIBLE
            this.reviews.visibility = View.VISIBLE

            this.reviews.removeAllViews()
            val inflater = activity?.layoutInflater
            for (review in reviews) {
                val reviewContainer = inflater?.inflate(R.layout.review, this.reviews,
                        false) as ViewGroup
                this.review_author.setText(review.author)
                this.review_content.setText(review.content)
                review_content.setOnClickListener(this)
                this.review.addView(reviewContainer)
            }
        }
    }

    override fun showFavorited() {
        favorite.setImageDrawable(
                context?.let { ContextCompat.getDrawable(it, R.drawable.ic_favorite_white_24dp) })
    }

    override fun showUnFavorited() {
        favorite.setImageDrawable(
                context?.let {
                    ContextCompat.getDrawable(it, R.drawable.ic_favorite_border_white_24dp)
                })
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.video_thumb -> onThumbnailClick(view)

            R.id.review_content -> onReviewClick(view as TextView)

            R.id.favorite -> onFavoriteClick()

            else -> {
            }
        }
    }

    private fun onReviewClick(view: TextView) {
        if (view.maxLines == 5) {
            view.maxLines = 500
        } else {
            view.maxLines = 5
        }
    }

    private fun onThumbnailClick(view: View) {
        val videoUrl = view.tag as String
        val playVideoIntent = Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl))
        startActivity(playVideoIntent)
    }

    private fun onFavoriteClick() {
        movie?.let { movieDetailsPresenter.onFavoriteClick(it) }
    }

    fun Video.getUrl(): String {
        return if (Constants.SITE_YOUTUBE.equals(this.site, ignoreCase = true)) {
            String.format(Constants.YOUTUBE_VIDEO_URL, this.videoId)
        } else {
            Constants.EMPTY
        }
    }

    fun Video.getThumbnailUrl(): String {
        return if (SITE_YOUTUBE.equals(this.site, ignoreCase = true)) {
            String.format(YOUTUBE_THUMBNAIL_URL, this.videoId)
        } else {
            Constants.EMPTY
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        movieDetailsPresenter.destroy()
    }
}