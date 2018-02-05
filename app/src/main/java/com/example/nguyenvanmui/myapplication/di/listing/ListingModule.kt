package com.example.nguyenvanmui.myapplication.di.listing

import com.example.nguyenvanmui.myapplication.domain.MoviesListingInteractor
import com.example.nguyenvanmui.myapplication.domain.MoviesListingInteractorImpl
import com.example.nguyenvanmui.myapplication.view.listing.ListingPresenter
import com.example.nguyenvanmui.myapplication.view.listing.ListingPresenterImpl
import dagger.Module
import dagger.Provides

/**
 * Created by nguyen.van.mui on 01/02/2018.
 */
@Module
class ListingModule {
    @Provides
    fun provideMovieListingInteractor(
            moviesListingInteractorImpl: MoviesListingInteractorImpl): MoviesListingInteractor {
        return moviesListingInteractorImpl
    }

    @Provides
    fun provideListingPresenter(listingPresenterImpl: ListingPresenterImpl): ListingPresenter {
        return listingPresenterImpl
    }
}