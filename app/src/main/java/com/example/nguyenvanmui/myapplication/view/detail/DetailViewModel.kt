package com.example.nguyenvanmui.myapplication.view.detail

import android.arch.lifecycle.*
import com.example.nguyenvanmui.myapplication.MainApplication
import com.example.nguyenvanmui.myapplication.data.remote.entity.Movie
import com.example.nguyenvanmui.myapplication.data.remote.entity.Review
import com.example.nguyenvanmui.myapplication.data.remote.entity.Video
import com.example.nguyenvanmui.myapplication.domain.FavoritesInteractor
import com.example.nguyenvanmui.myapplication.domain.MovieDetailInteractor
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


/**
 * Created by nguyen.van.mui on 02/02/2018.
 */
class DetailViewModel : ViewModel(), LifecycleObserver {

    @Inject
    lateinit var movieDetailsInteractor: MovieDetailInteractor
    @Inject
    lateinit var favoritesInteractor: FavoritesInteractor

    private val compositeDisposable = CompositeDisposable()

    init {
        MainApplication.appComponent.inject(this)
    }

    fun showTrailers(movie: Movie): LiveData<List<Video>> {
        return movieDetailsInteractor.getTrailers(movie.id)
    }

    fun showReviews(movie: Movie): LiveData<List<Review>> {
        return movieDetailsInteractor.getReviews(movie.id)
    }

    fun showFavoriteButton(movie: Movie): LiveData<Boolean> {
        var isFavorite = MutableLiveData<Boolean>()
        favoritesInteractor.isFavorite(movie.id).subscribe({
            isFavorite.value = true
        }, {
            it.printStackTrace()
            isFavorite.value = false
        }, {
            isFavorite.value = false
        })
        return isFavorite
    }

    fun onFavoriteClick(movie: Movie): LiveData<Boolean> {
        var isFavorite = MutableLiveData<Boolean>()
        favoritesInteractor.isFavorite(movie.id).subscribe({
            favoritesInteractor.unFavorite(movie.id)
            isFavorite.value = false
        }, {
            it.printStackTrace()
            isFavorite.value = false
        }, {
            favoritesInteractor.setFavorite(movie)
            isFavorite.value = true
        })
        return isFavorite
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun unSubscribeViewModel() {
        for (disposable in movieDetailsInteractor.allCompositeDisposable) {
            compositeDisposable.addAll(disposable)
        }
        for (disposable in favoritesInteractor.allCompositeDisposable) {
            compositeDisposable.addAll(disposable)
        }
        compositeDisposable.clear()
    }

    override fun onCleared() {
        unSubscribeViewModel()
        super.onCleared()
    }
}