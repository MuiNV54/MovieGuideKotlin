package com.example.nguyenvanmui.myapplication.domain

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.nguyenvanmui.myapplication.data.remote.entity.Movie
import com.example.nguyenvanmui.myapplication.data.repository.MovieRepository
import com.example.nguyenvanmui.myapplication.data.room.SortType
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

/**
 * Created by nguyen.van.mui on 01/02/2018.
 */
class MoviesListingInteractor @Inject constructor(
        var movieRepository: MovieRepository)  {
    val allCompositeDisposable: MutableList<Disposable> = arrayListOf()

    fun fetchMovies(): LiveData<List<Movie>> {
        val mutableLiveData = MutableLiveData<List<Movie>>()

        val disposable = getMoviesData().subscribe({ movies ->
            mutableLiveData.value = movies
        }, { it: Throwable? ->
            it!!.printStackTrace()
        })

        allCompositeDisposable.add(disposable)
        return mutableLiveData
    }

    fun getMoviesData(): Observable<List<Movie>> {
        when (movieRepository.getSelectedSortingOption()) {
            SortType.MOST_POPULAR.value ->
                return movieRepository.popularMovies()
                        .map { it.movies }
            SortType.HIGHEST_RATED.value ->
                return movieRepository.highestRatedMovies()
                        .map { it.movies }
            else ->
                return movieRepository.getRoomFavorites().toObservable()
        }
    }
}