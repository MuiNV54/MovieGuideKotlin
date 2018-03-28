package com.example.nguyenvanmui.myapplication.data.repository

import com.example.nguyenvanmui.myapplication.data.remote.entity.Movie
import com.example.nguyenvanmui.myapplication.data.remote.entity.MoviesResponse
import com.example.nguyenvanmui.myapplication.data.remote.entity.ReviewsResponse
import com.example.nguyenvanmui.myapplication.data.remote.entity.VideosResponse
import com.example.nguyenvanmui.myapplication.data.room.SortType
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable

/**
 * Created by nguyen.van.mui on 01/02/2018.
 */
interface MovieRepository {
    fun popularMovies(): Observable<MoviesResponse>

    fun highestRatedMovies(): Observable<MoviesResponse>

    fun trailers(movieId: String): Observable<VideosResponse>

    fun reviews(movieId: String): Observable<ReviewsResponse>

    fun setFavorite(movie: Movie): Completable

    fun getFavorite(id: String): Maybe<Movie>

    fun getRoomFavorites(): Maybe<List<Movie>>

    fun deleteFavorite(id: String): Completable

    fun getSelectedSortingOption(): Int

    fun setSortingOption(sortType: SortType)
}