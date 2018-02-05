package com.example.nguyenvanmui.myapplication.view.detail

import com.example.nguyenvanmui.myapplication.data.remote.entity.Movie

/**
 * Created by nguyen.van.mui on 02/02/2018.
 */
interface DetailPresenter {
    fun showDetails(movie: Movie)

    fun showTrailers(movie: Movie)

    fun showReviews(movie: Movie)

    fun showFavoriteButton(movie: Movie)

    fun onFavoriteClick(movie: Movie)

    fun setViewPresenter(view: DetailView)

    fun destroy()
}