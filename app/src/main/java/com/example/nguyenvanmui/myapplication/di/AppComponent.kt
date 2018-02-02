package com.example.nguyenvanmui.myapplication.di

import com.example.nguyenvanmui.myapplication.di.favorite.FavoriteModule
import com.example.nguyenvanmui.myapplication.di.listing.ListingComponent
import com.example.nguyenvanmui.myapplication.di.listing.ListingModule
import com.example.nguyenvanmui.myapplication.di.network.NetworkModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by nguyen.van.mui on 01/02/2018.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class, NetworkModule::class, FavoriteModule::class))
interface AppComponent {
    fun plus(listingModule: ListingModule): ListingComponent
}