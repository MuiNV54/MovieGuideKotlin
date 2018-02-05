package com.example.nguyenvanmui.myapplication.view.listing

/**
 * Created by nguyen.van.mui on 02/02/2018.
 */
interface ListingPresenter {
    fun displayMovies()

    fun setViewPresenter(view: ListingView)

    fun destroy()
}