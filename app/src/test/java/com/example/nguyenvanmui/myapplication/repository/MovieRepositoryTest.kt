package com.example.nguyenvanmui.myapplication.repository

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.nguyenvanmui.myapplication.RxImmediateSchedulerRule
import com.example.nguyenvanmui.myapplication.data.remote.TmdbWebService
import com.example.nguyenvanmui.myapplication.data.remote.entity.Movie
import com.example.nguyenvanmui.myapplication.data.remote.entity.Video
import com.example.nguyenvanmui.myapplication.data.remote.entity.VideosResponse
import com.example.nguyenvanmui.myapplication.data.repository.MovieRepository
import com.example.nguyenvanmui.myapplication.data.repository.MovieRepositoryImpl
import com.example.nguyenvanmui.myapplication.data.room.RoomFavoriteDao
import com.example.nguyenvanmui.myapplication.data.room.SortingOptionStore
import io.reactivex.Maybe
import io.reactivex.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.*


/**
 * Created by nguyen.van.mui on 12/04/2018.
 */

@RunWith(JUnit4::class)
class MovieRepositoryTest {
    lateinit var tmdbWebService: TmdbWebService
    lateinit var sortingOptionStore: SortingOptionStore
    lateinit var roomFavoriteDao: RoomFavoriteDao

    lateinit var movieRepository: MovieRepository

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()


    @Before
    fun setup() {
        tmdbWebService = mock<TmdbWebService>(TmdbWebService::class.java)
        sortingOptionStore = mock<SortingOptionStore>(SortingOptionStore::class.java)
        roomFavoriteDao = mock<RoomFavoriteDao>(RoomFavoriteDao::class.java)
        movieRepository = MovieRepositoryImpl(tmdbWebService, sortingOptionStore, roomFavoriteDao)
    }

    @Test
    fun testTrailers() {
        //true data return
        `when`(tmdbWebService.trailers("123")).thenReturn(Observable.just(createVideo2()))
        movieRepository.trailers("123").test().assertValue {
            it.videos?.size == 2
            it.videos?.get(1)?.name == "Video2";
            it.videos?.get(0)?.name == "Video1"
        }

        // error data return
        var runtimeException = RuntimeException("123");
        `when`(tmdbWebService.trailers("123")).thenReturn(Observable.error(runtimeException))
        movieRepository.trailers("123").test().assertError(runtimeException)

        verifyNoMoreInteractions(roomFavoriteDao)
        verifyNoMoreInteractions(sortingOptionStore)

        /*
        //test move to other function
        movieRepository.trailers("123")
        verify(tmdbWebService).trailers("123")
        */
    }

    @Test
    fun testGetFavoriteMovies() {
        var maybeMovies = Maybe.just(createMovie())
        `when`(roomFavoriteDao.getAllFavorites()).thenReturn(maybeMovies)
        movieRepository.getRoomFavorites().test().assertValue {
            it.size == 2;
            it.get(0).id == "123"
            it.get(1).id == "124"
        }
    }

    @Test
    fun testDeleteFavorite() {
        movieRepository.deleteFavorite("123").test()
        verify(roomFavoriteDao).deleteFavorite("123")
    }

    fun createVideo2(): VideosResponse {
        var video1: Video = Video("123", "Video1")
        var video2: Video = Video("124", "Video2")
        var response = VideosResponse(listOf<Video>(video1, video2))
        return response
    }

    fun createMovie(): List<Movie> {
        var movie1: Movie = Movie("123", "Movie1")
        var movie2: Movie = Movie("124", "Movie2")
        return listOf<Movie>(movie1, movie2)
    }
}