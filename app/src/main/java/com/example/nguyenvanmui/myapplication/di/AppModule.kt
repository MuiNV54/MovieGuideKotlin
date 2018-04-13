package com.example.nguyenvanmui.myapplication.di

import android.app.Application
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import android.content.res.Resources
import com.example.nguyenvanmui.myapplication.data.repository.MovieRepository
import com.example.nguyenvanmui.myapplication.data.repository.MovieRepositoryImpl
import com.example.nguyenvanmui.myapplication.view.CustomViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by nguyen.van.mui on 01/02/2018.
 */
@Module
class AppModule {
    var context: Context

    constructor(application: Application) {
        context = application
    }

    @Provides
    @Singleton
    fun provideContext(): Context {
        return context
    }

    @Provides
    @Singleton
    fun provideResources(context: Context): Resources {
        return context.resources
    }

    @Provides
    fun provideMoviesRepository(movieRepository: MovieRepositoryImpl): MovieRepository {
        return movieRepository
    }

    @Provides
    fun provideViewModelFactory(customViewModelFactory: CustomViewModelFactory): ViewModelProvider.Factory {
        return customViewModelFactory
    }
}