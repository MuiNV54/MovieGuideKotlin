package com.example.nguyenvanmui.myapplication.view.listing.sorting

/**
 * Created by nguyen.van.mui on 05/02/2018.
 */
interface SortingPresenter {
    fun setLastSavedOption()

    fun onPopularMoviesSelected()

    fun onHighestRatedMoviesSelected()

    fun onFavoritesSelected()

    fun setViewPresenter(view: SortingDialogView)

    fun destroy()
}