package com.example.nguyenvanmui.myapplication.view.listing

import android.content.Context
import android.graphics.Bitmap
import android.support.v4.content.ContextCompat
import android.support.v7.graphics.Palette
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.bumptech.glide.request.transition.Transition
import com.example.nguyenvanmui.myapplication.Constants
import com.example.nguyenvanmui.myapplication.R
import com.example.nguyenvanmui.myapplication.data.remote.entity.Movie

/**
 * Created by nguyen.van.mui on 05/02/2018.
 */
class ListingAdapter(var movies: List<Movie>,
        var view: ListingView) : RecyclerView.Adapter<ListingAdapter.ViewHolder>() {
    var context: Context? = null

    inner class ViewHolder(root: View) : RecyclerView.ViewHolder(root), View.OnClickListener {
        var name = root.findViewById<TextView>(R.id.movie_name)
        var poster = root.findViewById<ImageView>(R.id.movie_poster)
        var titleBackground = root.findViewById<View>(R.id.title_background)

        lateinit var movie: Movie

        override fun onClick(view: View) {
            this@ListingAdapter.view.onMovieClicked(movie)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val rootView = LayoutInflater.from(context).inflate(R.layout.movie_grid_item, parent, false)

        return ViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener(holder)
        holder.movie = movies[position]
        holder.name.setText(holder.movie.title)

        val options = RequestOptions()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .priority(Priority.HIGH)

        Glide.with(context)
                .asBitmap()
                .load(holder.movie.posterPath?.let { Constants.getPosterPath(it) })
                .apply(options)
                .into(object : BitmapImageViewTarget(holder.poster) {
                    override fun onResourceReady(bitmap: Bitmap,
                            transition: Transition<in Bitmap>?) {
                        super.onResourceReady(bitmap, transition)
                        Palette.from(bitmap).generate(
                                { palette -> setBackgroundColor(palette, holder) })
                    }
                })
    }

    private fun setBackgroundColor(palette: Palette, holder: ViewHolder) {
        holder.titleBackground.setBackgroundColor(palette.getVibrantColor(
                ContextCompat.getColor(context!!, R.color.black_translucent_60)))
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}