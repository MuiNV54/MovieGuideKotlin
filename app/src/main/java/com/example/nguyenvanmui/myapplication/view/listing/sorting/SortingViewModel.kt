package com.example.nguyenvanmui.myapplication.view.listing.sorting

import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.ViewModel
import com.example.nguyenvanmui.myapplication.MainApplication
import com.example.nguyenvanmui.myapplication.data.room.SortType
import com.example.nguyenvanmui.myapplication.domain.SortingInteractor
import javax.inject.Inject

/**
 * Created by nguyen.van.mui on 05/02/2018.
 */
class SortingViewModel : ViewModel(), LifecycleObserver {
    @Inject
    lateinit var sortingInteractor: SortingInteractor

    init {
        MainApplication.appComponent.inject(this)
    }

    fun setLastSavedOption(): Int {
        return sortingInteractor.getSelectedSortingOption()
    }

    fun onPopularMoviesSelected() {
        sortingInteractor.setSortingOption(SortType.MOST_POPULAR)
    }

    fun onHighestRatedMoviesSelected() {
        sortingInteractor.setSortingOption(SortType.HIGHEST_RATED)
    }

    fun onFavoritesSelected() {
        sortingInteractor.setSortingOption(SortType.FAVORITES)
    }
}