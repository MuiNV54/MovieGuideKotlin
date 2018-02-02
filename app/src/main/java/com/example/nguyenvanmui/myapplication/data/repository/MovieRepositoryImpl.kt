package com.example.nguyenvanmui.myapplication.data.repository

import com.example.nguyenvanmui.myapplication.data.remote.TmdbWebService
import com.example.nguyenvanmui.myapplication.data.remote.entity.Movie
import com.example.nguyenvanmui.myapplication.data.remote.entity.MoviesResponse
import com.example.nguyenvanmui.myapplication.data.remote.entity.ReviewsResponse
import com.example.nguyenvanmui.myapplication.data.remote.entity.VideosResponse
import com.example.nguyenvanmui.myapplication.data.room.FavoritesStore
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by nguyen.van.mui on 01/02/2018.
 */
class MovieRepositoryImpl @Inject constructor(var webService: TmdbWebService,
        var favoritesStore: FavoritesStore) : MovieRepository {
    override fun setFavorite(movie: Movie) {
        favoritesStore.setFavorite(movie)
    }

    override fun isFavorite(id: String): Boolean {
        return favoritesStore.isFavorite(id)
    }

    override fun getFavorites(): List<Movie> {
        return favoritesStore.getFavorites()
    }

    override fun unFavorite(id: String) {
        return favoritesStore.unfavorite(id)
    }

    override fun popularMovies(): Observable<MoviesResponse> {
        return webService.popularMovies().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun highestRatedMovies(): Observable<MoviesResponse> {
        return webService.highestRatedMovies().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun trailers(movieId: String): Observable<VideosResponse> {
        return webService.trailers(movieId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun reviews(movieId: String): Observable<ReviewsResponse> {
        return webService.reviews(movieId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}