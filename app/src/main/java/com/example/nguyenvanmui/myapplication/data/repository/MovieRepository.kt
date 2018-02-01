package com.example.nguyenvanmui.myapplication.data.repository

import com.example.nguyenvanmui.myapplication.data.remote.entity.MoviesResponse
import com.example.nguyenvanmui.myapplication.data.remote.entity.ReviewsResponse
import com.example.nguyenvanmui.myapplication.data.remote.entity.VideosResponse
import io.reactivex.Observable

/**
 * Created by nguyen.van.mui on 01/02/2018.
 */
interface MovieRepository {
    fun popularMovies(): Observable<MoviesResponse>

    fun highestRatedMovies(): Observable<MoviesResponse>

    fun trailers(movieId: String): Observable<VideosResponse>

    fun reviews(movieId: String): Observable<ReviewsResponse>
}