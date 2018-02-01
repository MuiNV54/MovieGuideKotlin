package com.example.nguyenvanmui.myapplication.data.remote

import com.example.nguyenvanmui.myapplication.data.remote.entity.MoviesResponse
import com.example.nguyenvanmui.myapplication.data.remote.entity.ReviewsResponse
import com.example.nguyenvanmui.myapplication.data.remote.entity.VideosResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by nguyen.van.mui on 01/02/2018.
 */
interface TmdbWebService {
    @GET("3/discover/movie?language=en&sort_by=popularity.desc")
    fun popularMovies(): Observable<MoviesResponse>

    @GET("3/discover/movie?vote_count.gte=500&language=en&sort_by=vote_average.desc")
    fun highestRatedMovies(): Observable<MoviesResponse>

    @GET("3/movie/{movieId}/videos")
    fun trailers(@Path("movieId") movieId: String): Observable<VideosResponse>

    @GET("3/movie/{movieId}/reviews")
    fun reviews(@Path("movieId") movieId: String): Observable<ReviewsResponse>
}