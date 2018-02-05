package com.example.nguyenvanmui.myapplication.view.listing

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.example.nguyenvanmui.myapplication.MainApplication
import com.example.nguyenvanmui.myapplication.R
import com.example.nguyenvanmui.myapplication.data.remote.entity.Movie
import com.example.nguyenvanmui.myapplication.view.listing.sorting.SortingDialogFragment
import kotlinx.android.synthetic.main.fragment_movies.*
import java.util.*
import javax.inject.Inject

/**
 * Created by nguyen.van.mui on 02/02/2018.
 */
class ListingFragment : Fragment(), ListingView {

    @Inject
    lateinit var moviesPresenter: ListingPresenter

    private var adapter: RecyclerView.Adapter<*>? = null
    private val movies = ArrayList<Movie>(20)
    private var callback: Callback? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        callback = context as Callback?
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        retainInstance = true
        (activity?.application as MainApplication).createListingComponent().inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_movies, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLayoutReferences()
        moviesPresenter.setViewPresenter(this)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.action_sort -> displaySortingOptions()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun displaySortingOptions() {
        val sortingDialogFragment = SortingDialogFragment.newInstance(moviesPresenter)
        sortingDialogFragment.show(fragmentManager, "Select Quantity")
    }

    private fun initLayoutReferences() {
        movies_listing.setHasFixedSize(true)

        val columns: Int
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            columns = 2
        } else {
            columns = resources.getInteger(R.integer.no_of_columns)
        }
        val layoutManager = GridLayoutManager(activity, columns)

        movies_listing.layoutManager = layoutManager
        adapter = ListingAdapter(movies, this)
        movies_listing.adapter = adapter
    }

    override fun showMovies(movies: List<Movie>) {
        this.movies.clear()
        this.movies.addAll(movies)
        movies_listing.visibility = View.VISIBLE
        adapter?.notifyDataSetChanged()
        callback?.onMoviesLoaded(movies[0])
    }

    override fun loadingStarted() {
        Snackbar.make(movies_listing, R.string.loading_movies, Snackbar.LENGTH_SHORT).show()
    }

    override fun loadingFailed(errorMessage: String) {
        Snackbar.make(movies_listing!!, errorMessage, Snackbar.LENGTH_INDEFINITE).show()
    }

    override fun onMovieClicked(movie: Movie) {
        callback!!.onMovieClicked(movie)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        moviesPresenter.destroy()
    }

    override fun onDetach() {
        callback = null
        super.onDetach()
    }

    interface Callback {
        fun onMoviesLoaded(movie: Movie)

        fun onMovieClicked(movie: Movie)
    }
}