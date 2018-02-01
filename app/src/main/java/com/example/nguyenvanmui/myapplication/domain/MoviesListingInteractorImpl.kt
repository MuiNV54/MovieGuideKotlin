package com.example.nguyenvanmui.myapplication.domain

import com.example.nguyenvanmui.myapplication.data.remote.entity.Movie
import com.example.nguyenvanmui.myapplication.data.repository.MovieRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by nguyen.van.mui on 01/02/2018.
 */
class MoviesListingInteractorImpl @Inject constructor(
        var movieRepository: MovieRepository) : MoviesListingInteractor {
    override fun fetchMovies(): Observable<List<Movie>> {
        return movieRepository.popularMovies()
                .map { it.movies }
    }
}