package com.example.nguyenvanmui.myapplication.data.repository

import com.example.nguyenvanmui.myapplication.data.remote.TmdbWebService
import com.example.nguyenvanmui.myapplication.data.remote.entity.Movie
import com.example.nguyenvanmui.myapplication.data.remote.entity.MoviesResponse
import com.example.nguyenvanmui.myapplication.data.remote.entity.ReviewsResponse
import com.example.nguyenvanmui.myapplication.data.remote.entity.VideosResponse
import com.example.nguyenvanmui.myapplication.data.room.RoomFavoriteDataSource
import com.example.nguyenvanmui.myapplication.data.room.SortType
import com.example.nguyenvanmui.myapplication.data.room.SortingOptionStore
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by nguyen.van.mui on 01/02/2018.
 */
class MovieRepositoryImpl @Inject constructor(var webService: TmdbWebService,
        var sortingOptionStore: SortingOptionStore,
        var roomFavoriteDataSource: RoomFavoriteDataSource) : MovieRepository {
    override fun deleteFavorite(id: String): Completable {
        return Completable.fromAction {
            roomFavoriteDataSource.favoriteDao().deleteFavorite(id)
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getFavorite(id: String): Maybe<Movie> {
        return roomFavoriteDataSource.favoriteDao().getFavorite(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getRoomFavorites(): Flowable<List<Movie>> {
        return roomFavoriteDataSource.favoriteDao().getAllFavorites()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getSelectedSortingOption(): Int {
        return sortingOptionStore.getSelectedOption()
    }

    override fun setSortingOption(sortType: SortType) {
        sortingOptionStore.setSelectedOption(sortType)
    }

    override fun setFavorite(movie: Movie): Completable {
        return Completable.fromAction {
            roomFavoriteDataSource.favoriteDao().insert(movie)
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
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