package com.example.nguyenvanmui.myapplication

import android.app.Application
import android.os.StrictMode
import com.example.nguyenvanmui.myapplication.di.AppComponent
import com.example.nguyenvanmui.myapplication.di.AppModule
import com.example.nguyenvanmui.myapplication.di.DaggerAppComponent
import com.example.nguyenvanmui.myapplication.di.data.NetworkModule
import com.example.nguyenvanmui.myapplication.di.data.RoomModule

/**
 * Created by nguyen.van.mui on 01/02/2018.
 */
class MainApplication : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

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
}