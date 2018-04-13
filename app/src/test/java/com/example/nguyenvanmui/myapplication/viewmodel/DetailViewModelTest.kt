package com.example.nguyenvanmui.myapplication.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import com.example.nguyenvanmui.myapplication.data.remote.entity.Movie
import com.example.nguyenvanmui.myapplication.domain.FavoritesInteractor
import com.example.nguyenvanmui.myapplication.domain.MovieDetailInteractor
import com.example.nguyenvanmui.myapplication.view.detail.DetailViewModel
import io.reactivex.Maybe
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*

/**
 * Created by nguyen.van.mui on 13/04/2018.
 */
@RunWith(JUnit4::class)
class DetailViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    lateinit var detailViewModel: DetailViewModel
    lateinit var movieDetailsInteractor: MovieDetailInteractor
    lateinit var favoritesInteractor: FavoritesInteractor

    @Before
    fun setup() {
        movieDetailsInteractor = mock<MovieDetailInteractor>(MovieDetailInteractor::class.java)
        favoritesInteractor = mock<FavoritesInteractor>(FavoritesInteractor::class.java)
        detailViewModel = DetailViewModel(movieDetailsInteractor, favoritesInteractor)
    }

    @Test
    fun testNull() {
        verify(movieDetailsInteractor, never()).getTrailers(ArgumentMatchers.anyString())
        verify(favoritesInteractor, never()).isFavorite(ArgumentMatchers.anyString())
    }

    @Test
    fun testShowReviews() {
        detailViewModel.showReviews(createTestMoview())
        verify(movieDetailsInteractor).getReviews("123")
    }

    @Test
    fun testShowTrailer() {
        detailViewModel.showTrailers(createTestMoview())
        verify(movieDetailsInteractor).getTrailers("123")
    }

    @Test
    fun testshowFavoriteButton() {
        // verify jump to right function
        `when`(favoritesInteractor.isFavorite("123")).thenReturn(Maybe.just(createTestMoview()))
        detailViewModel.showFavoriteButton(createTestMoview())
        verify(favoritesInteractor).isFavorite("123")

        // verify true data
        var observer = mock(Observer::class.java) as Observer<Boolean>
        detailViewModel.showFavoriteButton(createTestMoview()).observeForever(observer)
        verify(observer).onChanged(true)

        `when`(favoritesInteractor.isFavorite("124")).thenReturn(Maybe.error(RuntimeException("test")))
        var observer1 = mock(Observer::class.java) as Observer<Boolean>
        detailViewModel.showFavoriteButton(createTestMoview2()).observeForever(observer1)
        verify(observer1).onChanged(false)
    }

    fun createTestMoview(): Movie {
        var movie = Movie("123", "Movie1")
        return movie
    }

    fun createTestMoview2(): Movie {
        var movie = Movie("124", "Movie1")
        return movie
    }
}