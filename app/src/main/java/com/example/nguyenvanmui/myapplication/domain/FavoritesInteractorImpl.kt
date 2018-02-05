package com.example.nguyenvanmui.myapplication.domain

import com.example.nguyenvanmui.myapplication.data.remote.entity.Movie
import com.example.nguyenvanmui.myapplication.data.repository.MovieRepository
import io.reactivex.Flowable
import io.reactivex.Maybe
import javax.inject.Inject

/**
 * Created by nguyen.van.mui on 02/02/2018.
 */
class FavoritesInteractorImpl @Inject constructor(
        var movieRepository: MovieRepository) : FavoritesInteractor {

    override fun setFavorite(movie: Movie) {
        return movieRepository.setFavorite(movie)
    }

    override fun isFavorite(id: String): Maybe<Movie> {
        return movieRepository.getFavorite(id)
    }

    override fun getFavorites(): Flowable<List<Movie>> {
        return movieRepository.getRoomFavorites()
    }

    override fun unFavorite(id: String) {
        movieRepository.deleteFavorite(id)
    }
}