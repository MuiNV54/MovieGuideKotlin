package com.example.nguyenvanmui.myapplication.domain

import com.example.nguyenvanmui.myapplication.data.remote.entity.Movie
import com.example.nguyenvanmui.myapplication.data.repository.MovieRepository
import io.reactivex.Maybe
import io.reactivex.disposables.Disposable
import javax.inject.Inject

/**
 * Created by nguyen.van.mui on 02/02/2018.
 */
class FavoritesInteractor @Inject constructor(
        var movieRepository: MovieRepository) {
    val allCompositeDisposable: MutableList<Disposable> = arrayListOf()

    fun setFavorite(movie: Movie) {
        allCompositeDisposable.add(movieRepository.setFavorite(movie)
                .subscribe({}))
    }

    fun isFavorite(id: String): Maybe<Movie> {
        return movieRepository.getFavorite(id)
    }

    fun unFavorite(id: String) {
        allCompositeDisposable.add(movieRepository.deleteFavorite(id).subscribe({}))
    }
}