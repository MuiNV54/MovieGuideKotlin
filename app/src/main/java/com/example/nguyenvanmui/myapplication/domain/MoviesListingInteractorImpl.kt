package com.example.nguyenvanmui.myapplication.domain

import com.example.nguyenvanmui.myapplication.data.remote.entity.Movie
import com.example.nguyenvanmui.myapplication.data.repository.MovieRepository
import com.example.nguyenvanmui.myapplication.data.room.SortType
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by nguyen.van.mui on 01/02/2018.
 */
class MoviesListingInteractorImpl @Inject constructor(
        var movieRepository: MovieRepository) : MoviesListingInteractor {
    override fun fetchMovies(): Observable<List<Movie>> {
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