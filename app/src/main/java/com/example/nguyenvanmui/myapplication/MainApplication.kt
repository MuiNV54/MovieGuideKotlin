package com.example.nguyenvanmui.myapplication

import android.app.Application
import android.os.StrictMode
import com.example.nguyenvanmui.myapplication.di.AppComponent
import com.example.nguyenvanmui.myapplication.di.AppModule
import com.example.nguyenvanmui.myapplication.di.DaggerAppComponent
import com.example.nguyenvanmui.myapplication.di.data.NetworkModule
import com.example.nguyenvanmui.myapplication.di.data.RoomModule
import com.example.nguyenvanmui.myapplication.di.detail.DetailComponent
import com.example.nguyenvanmui.myapplication.di.detail.DetailModule
import com.example.nguyenvanmui.myapplication.di.listing.ListingComponent
import com.example.nguyenvanmui.myapplication.di.listing.ListingModule

/**
 * Created by nguyen.van.mui on 01/02/2018.
 */
class MainApplication : Application() {
    lateinit var appComponent: AppComponent
    lateinit var listingComponent: ListingComponent
    lateinit var detailComponent: DetailComponent

    override fun onCreate() {
        super.onCreate()
        StrictMode.enableDefaults()
        appComponent = createAppComponent()
    }

    private fun createAppComponent(): AppComponent {
        return DaggerAppComponent.builder()
                .appModule(AppModule(this@MainApplication))
                .networkModule(NetworkModule())
                .roomModule(RoomModule())
                .build()
    }

    fun createListingComponent(): ListingComponent {
        listingComponent = appComponent.plus(ListingModule())
        return listingComponent
    }

    fun createDetailComponent(): DetailComponent {
        detailComponent = appComponent.plus(DetailModule())
        return detailComponent
    }
}