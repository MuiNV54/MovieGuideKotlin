package com.example.nguyenvanmui.myapplication.view.listing.sorting

import com.example.nguyenvanmui.myapplication.data.room.SortType
import com.example.nguyenvanmui.myapplication.domain.SortingInteractor
import javax.inject.Inject

/**
 * Created by nguyen.van.mui on 05/02/2018.
 */
class SortingPresenterImpl @Inject constructor(
        var sortingInteractor: SortingInteractor) : SortingPresenter {
    private var view: SortingDialogView? = null

    override fun setViewPresenter(view: SortingDialogView) {
        this.view = view
    }

    override fun destroy() {
        view = null
    }

    override fun setLastSavedOption() {
        if (isViewAttached()) {
            val selectedOption = sortingInteractor.getSelectedSortingOption()

            if (selectedOption == SortType.MOST_POPULAR.value) {
                view?.setPopularChecked()
            } else if (selectedOption == SortType.HIGHEST_RATED.value) {
                view?.setHighestRatedChecked()
            } else {
                view?.setFavoritesChecked()
            }
        }
    }

    private fun isViewAttached(): Boolean {
        return view != null
    }

    override fun onPopularMoviesSelected() {
        if (isViewAttached()) {
            sortingInteractor.setSortingOption(SortType.MOST_POPULAR)
            view?.dismissDialog()
        }
    }

    override fun onHighestRatedMoviesSelected() {
        if (isViewAttached()) {
            sortingInteractor.setSortingOption(SortType.HIGHEST_RATED)
            view!!.dismissDialog()
        }
    }

    override fun onFavoritesSelected() {
        if (isViewAttached()) {
            sortingInteractor.setSortingOption(SortType.FAVORITES)
            view?.dismissDialog()
        }
    }
}