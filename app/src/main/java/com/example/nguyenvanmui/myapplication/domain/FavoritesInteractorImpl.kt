package com.example.nguyenvanmui.myapplication.domain

import com.example.nguyenvanmui.myapplication.data.remote.entity.Movie
import com.example.nguyenvanmui.myapplication.data.repository.MovieRepository
import javax.inject.Inject

/**
 * Created by nguyen.van.mui on 02/02/2018.
 */
class FavoritesInteractorImpl @Inject constructor(
        var movieRepository: MovieRepository) : FavoritesInteractor {

    override fun setFavorite(movie: Movie) {
        movieRepository.setFavorite(movie)
    }

    override fun isFavorite(id: String): Boolean {
        return movieRepository.isFavorite(id)
    }

    override fun getFavorites(): List<Movie> {
        return movieRepository.getFavorites()
    }

    override fun unFavorite(id: String) {
        movieRepository.unFavorite(id)
    }
}