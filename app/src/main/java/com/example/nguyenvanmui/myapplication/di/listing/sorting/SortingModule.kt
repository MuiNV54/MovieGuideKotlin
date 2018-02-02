package com.example.nguyenvanmui.myapplication.di.listing.sorting

import com.example.nguyenvanmui.myapplication.domain.SortingInteractor
import com.example.nguyenvanmui.myapplication.domain.SortingInteractorImpl
import dagger.Module
import dagger.Provides

/**
 * Created by nguyen.van.mui on 02/02/2018.
 */
@Module
class SortingModule {
    @Provides
    fun providesSortingDialogInteractor(
            sortingInteractorImpl: SortingInteractorImpl): SortingInteractor {
        return sortingInteractorImpl
    }
}