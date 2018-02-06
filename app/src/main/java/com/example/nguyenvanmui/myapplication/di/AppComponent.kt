package com.example.nguyenvanmui.myapplication.di

import com.example.nguyenvanmui.myapplication.di.data.NetworkModule
import com.example.nguyenvanmui.myapplication.di.data.RoomModule
import com.example.nguyenvanmui.myapplication.view.detail.DetailViewModel
import com.example.nguyenvanmui.myapplication.view.listing.ListingViewModel
import com.example.nguyenvanmui.myapplication.view.listing.sorting.SortingViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * Created by nguyen.van.mui on 01/02/2018.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class, NetworkModule::class, RoomModule::class))
interface AppComponent {

    fun inject(listingViewModel: ListingViewModel)

    fun inject(sortingViewModel: SortingViewModel)

    fun inject(detailPresenterImpl: DetailViewModel)
}