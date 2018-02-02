package com.example.nguyenvanmui.myapplication.domain

import com.example.nguyenvanmui.myapplication.data.remote.entity.Movie

/**
 * Created by nguyen.van.mui on 02/02/2018.
 */
interface FavoritesInteractor {
    fun setFavorite(movie: Movie)

    fun isFavorite(id: String): Boolean

    fun getFavorites(): List<Movie>

    fun unFavorite(id: String)
}