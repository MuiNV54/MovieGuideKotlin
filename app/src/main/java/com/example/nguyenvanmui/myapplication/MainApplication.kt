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

/**
 * Created by nguyen.van.mui on 01/02/2018.
 */
class MainApplication : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    var detailComponent: DetailComponent? = null

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

    fun createDetailComponent(): DetailComponent {
        if (detailComponent == null) {
            detailComponent = appComponent.plus(DetailModule())
        }
        return detailComponent as DetailComponent
    }
}