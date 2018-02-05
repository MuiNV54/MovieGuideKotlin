package com.example.nguyenvanmui.myapplication.view.listing

import com.example.nguyenvanmui.myapplication.data.remote.entity.Movie

/**
 * Created by nguyen.van.mui on 02/02/2018.
 */
interface ListingView {
    fun showMovies(movies: List<Movie>)

    fun loadingStarted()

    fun loadingFailed(errorMessage: String)

    fun onMovieClicked(movie: Movie)
}