package com.example.nguyenvanmui.myapplication.view

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.nguyenvanmui.myapplication.domain.FavoritesInteractor
import com.example.nguyenvanmui.myapplication.domain.MovieDetailInteractor
import com.example.nguyenvanmui.myapplication.view.detail.DetailViewModel
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by nguyen.van.mui on 13/04/2018.
 */
@Singleton
class CustomViewModelFactory @Inject constructor(var favoritesInteractor: FavoritesInteractor,
        var movieDetailsInteractor: MovieDetailInteractor) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailViewModel(movieDetailsInteractor, favoritesInteractor) as T
    }
}