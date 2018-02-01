package com.example.nguyenvanmui.myapplication.domain

import com.example.nguyenvanmui.myapplication.data.remote.entity.Movie
import io.reactivex.Observable

/**
 * Created by nguyen.van.mui on 01/02/2018.
 */
interface MoviesListingInteractor {
    fun fetchMovies(): Observable<List<Movie>>
}