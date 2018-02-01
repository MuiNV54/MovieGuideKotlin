package com.example.nguyenvanmui.myapplication

import android.app.Application
import android.os.StrictMode
import com.example.nguyenvanmui.myapplication.di.AppComponent
import com.example.nguyenvanmui.myapplication.di.AppModule
import com.example.nguyenvanmui.myapplication.di.DaggerAppComponent
import com.example.nguyenvanmui.myapplication.di.listing.ListingComponent
import com.example.nguyenvanmui.myapplication.di.listing.ListingModule
import com.example.nguyenvanmui.myapplication.di.network.NetworkModule

/**
 * Created by nguyen.van.mui on 01/02/2018.
 */
class MainApplication : Application() {
    lateinit var appComponent: AppComponent
    lateinit var listingComponent: ListingComponent

    override fun onCreate() {
        super.onCreate()
        StrictMode.enableDefaults()
        appComponent = createAppComponent()
    }

    private fun createAppComponent(): AppComponent {
        return DaggerAppComponent.builder()
                .appModule(AppModule(this@MainApplication))
                .networkModule(NetworkModule())
                .build()
    }

    fun createListingComponent(): ListingComponent {
        listingComponent = appComponent.plus(ListingModule())
        return listingComponent
    }
}