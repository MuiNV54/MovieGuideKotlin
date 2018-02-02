package com.example.nguyenvanmui.myapplication.di.detail

import com.example.nguyenvanmui.myapplication.domain.MovieDetailInteractor
import com.example.nguyenvanmui.myapplication.domain.MovieDetailInteractorImpl
import dagger.Module
import dagger.Provides

/**
 * Created by nguyen.van.mui on 02/02/2018.
 */
@Module
class DetailModule {

    @Provides
    @DetailsScope
    fun provideInteractor(
            movieDetailInteractorImpl: MovieDetailInteractorImpl): MovieDetailInteractor {
        return movieDetailInteractorImpl
    }
}