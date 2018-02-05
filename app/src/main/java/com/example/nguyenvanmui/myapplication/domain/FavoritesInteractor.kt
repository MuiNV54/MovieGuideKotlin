package com.example.nguyenvanmui.myapplication.domain

import com.example.nguyenvanmui.myapplication.data.remote.entity.Movie
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Observable

/**
 * Created by nguyen.van.mui on 02/02/2018.
 */
interface FavoritesInteractor {
    fun setFavorite(movie: Movie)

    fun isFavorite(id: String): Maybe<Movie>

    fun getFavorites(): Flowable<List<Movie>>

    fun unFavorite(id: String)
}