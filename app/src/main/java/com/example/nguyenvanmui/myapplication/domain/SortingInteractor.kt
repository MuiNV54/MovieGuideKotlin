package com.example.nguyenvanmui.myapplication.domain

import com.example.nguyenvanmui.myapplication.data.room.SortType
import com.example.nguyenvanmui.myapplication.data.room.SortingOptionStore
import javax.inject.Inject

/**
 * Created by nguyen.van.mui on 02/02/2018.
 */
class SortingInteractor @Inject constructor(
        var sortingOptionStore: SortingOptionStore) {
    fun getSelectedSortingOption(): Int {
        return sortingOptionStore.getSelectedOption()
    }

    fun setSortingOption(sortType: SortType) {
        sortingOptionStore.setSelectedOption(sortType)
    }
}