package com.example.nguyenvanmui.myapplication.view.detail

import com.example.nguyenvanmui.myapplication.data.remote.entity.Movie
import com.example.nguyenvanmui.myapplication.data.remote.entity.Review
import com.example.nguyenvanmui.myapplication.data.remote.entity.Video
import com.example.nguyenvanmui.myapplication.domain.FavoritesInteractor
import com.example.nguyenvanmui.myapplication.domain.MovieDetailInteractor
import com.example.nguyenvanmui.myapplication.util.RxUtils
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


/**
 * Created by nguyen.van.mui on 02/02/2018.
 */
class DetailPresenterImpl : DetailPresenter {
    lateinit var view: DetailView
    val movieDetailsInteractor: MovieDetailInteractor
    val favoritesInteractor: FavoritesInteractor
    lateinit var trailersSubscription: Disposable
    lateinit var reviewSubscription: Disposable

    @Inject
    constructor(movieDetailInteractor: MovieDetailInteractor,
            favoritesInteractor: FavoritesInteractor) {
        this.movieDetailsInteractor = movieDetailInteractor
        this.favoritesInteractor = favoritesInteractor
    }

    override fun setViewPresenter(view: DetailView) {
        this.view = view
    }

    override fun destroy() {
        RxUtils.unsubscribe(trailersSubscription)
        RxUtils.unsubscribe(reviewSubscription)
    }

    override fun showDetails(movie: Movie) {
        if (isViewAttached()) {
            view.showDetails(movie)

            Completable.fromAction {
                favoritesInteractor.setFavorite(movie)
            }.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }

    private fun isViewAttached(): Boolean {
        return view != null
    }

    override fun showTrailers(movie: Movie) {
        trailersSubscription = movieDetailsInteractor.getTrailers(movie.id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ this.onGetTrailersSuccess(it) }, { t -> onGetTrailersFailure() })
    }

    private fun onGetTrailersSuccess(videos: List<Video>) {
        if (isViewAttached()) {
            view.showTrailers(videos)
        }
    }

    private fun onGetTrailersFailure() {
        // Do nothing
    }

    override fun showReviews(movie: Movie) {
        reviewSubscription = movieDetailsInteractor.getReviews(movie.id)
                .subscribe({ this.onGetReviewsSuccess(it) }, { t -> onGetReviewsFailure() })
    }

    private fun onGetReviewsSuccess(reviews: List<Review>) {
        if (isViewAttached()) {
            view.showReviews(reviews)
        }
    }

    private fun onGetReviewsFailure() {
        // Do nothing
    }

    override fun showFavoriteButton(movie: Movie) {
        favoritesInteractor.isFavorite(movie.id).subscribe({
            if (isViewAttached())
                view.showFavorited()
        }, {
            it.printStackTrace()
        }, {
            if (isViewAttached())
                view.showUnFavorited()
        })
    }

    override fun onFavoriteClick(movie: Movie) {
        favoritesInteractor.isFavorite(movie.id).subscribe({
            if (isViewAttached()) {
                Completable.fromAction {
                    favoritesInteractor.unFavorite(movie.id)
                }.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe { }
                view.showUnFavorited()
            }
        }, {
            it.printStackTrace()
        }, {
            if (isViewAttached()) {
                Completable.fromAction {
                    favoritesInteractor.setFavorite(movie)
                }.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe { }
                view.showFavorited()
            }
        })
    }
}