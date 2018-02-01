package com.example.nguyenvanmui.myapplication.di.listing

import com.example.nguyenvanmui.myapplication.data.repository.MovieRepository
import com.example.nguyenvanmui.myapplication.data.repository.MovieRepositoryImpl
import com.example.nguyenvanmui.myapplication.domain.MoviesListingInteractor
import com.example.nguyenvanmui.myapplication.domain.MoviesListingInteractorImpl
import dagger.Module
import dagger.Provides

/**
 * Created by nguyen.van.mui on 01/02/2018.
 */
@Module
class ListingModule {
    @Provides
    internal fun provideMovieListingInteractor(
            moviesListingInteractorImpl: MoviesListingInteractorImpl): MoviesListingInteractor {
        return moviesListingInteractorImpl
    }

    @Provides
    fun provideMoviesRepository(movieRepository: MovieRepositoryImpl): MovieRepository {
        return movieRepository
    }
}