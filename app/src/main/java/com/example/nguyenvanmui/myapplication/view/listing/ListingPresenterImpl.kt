package com.example.nguyenvanmui.myapplication.view.listing

import com.example.nguyenvanmui.myapplication.data.remote.entity.Movie
import com.example.nguyenvanmui.myapplication.domain.MoviesListingInteractor
import com.example.nguyenvanmui.myapplication.util.RxUtils
import io.reactivex.disposables.Disposable
import javax.inject.Inject

/**
 * Created by nguyen.van.mui on 02/02/2018.
 */
class ListingPresenterImpl : ListingPresenter {
    lateinit var view: ListingView
    var moviesInteractor: MoviesListingInteractor
    private var fetchSubscription: Disposable? = null

    @Inject constructor(interactor: MoviesListingInteractor) {
        moviesInteractor = interactor
    }

    override fun setViewPresenter(view: ListingView) {
        this.view = view
        displayMovies()
    }

    override fun destroy() {
        RxUtils.unsubscribe(fetchSubscription)
    }

    override fun displayMovies() {
        showLoading()
        fetchSubscription = moviesInteractor.fetchMovies()
                .subscribe({ this.onMovieFetchSuccess(it) }, {
                    this.onMovieFetchFailed(it)
                })
    }

    private fun showLoading() {
        if (isViewAttached()) {
            view.loadingStarted()
        }
    }

    private fun onMovieFetchSuccess(movies: List<Movie>) {
        if (isViewAttached()) {
            view.showMovies(movies)
        }
    }

    private fun onMovieFetchFailed(e: Throwable) {
        e.message?.let { view.loadingFailed(it) }
    }

    private fun isViewAttached(): Boolean {
        return view != null
    }
}