package com.example.nguyenvanmui.myapplication.domain

import com.example.nguyenvanmui.myapplication.data.room.SortType

/**
 * Created by nguyen.van.mui on 02/02/2018.
 */
interface SortingInteractor {
    fun getSelectedSortingOption(): Int

    fun setSortingOption(sortType: SortType)
}