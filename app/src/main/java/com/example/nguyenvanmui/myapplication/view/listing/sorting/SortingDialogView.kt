package com.example.nguyenvanmui.myapplication.view.listing.sorting

/**
 * Created by nguyen.van.mui on 05/02/2018.
 */
interface SortingDialogView {
    fun setPopularChecked()

    fun setHighestRatedChecked()

    fun setFavoritesChecked()

    fun dismissDialog()
}