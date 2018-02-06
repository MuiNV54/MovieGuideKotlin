package com.example.nguyenvanmui.myapplication.view.listing

import android.arch.lifecycle.*
import com.example.nguyenvanmui.myapplication.MainApplication
import com.example.nguyenvanmui.myapplication.data.remote.entity.Movie
import com.example.nguyenvanmui.myapplication.domain.MoviesListingInteractorImpl
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by nguyen.van.mui on 02/02/2018.
 */
class ListingViewModel : ViewModel(), LifecycleObserver {
    lateinit var view: ListingView
    private val compositeDisposable = CompositeDisposable()

    @Inject
    lateinit var moviesInteractor: MoviesListingInteractorImpl

    init {
        MainApplication.appComponent.inject(this)
    }

    fun loadMovies(): LiveData<List<Movie>> {
        return moviesInteractor.fetchMovies()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun unSubscribeViewModel() {
        for (disposable in moviesInteractor.allCompositeDisposable) {
            compositeDisposable.add(disposable)
        }

        compositeDisposable.clear()
    }

    override fun onCleared() {
        unSubscribeViewModel()
        super.onCleared()
    }
}