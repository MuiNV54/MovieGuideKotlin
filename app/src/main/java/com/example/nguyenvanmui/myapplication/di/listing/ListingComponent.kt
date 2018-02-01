package com.example.nguyenvanmui.myapplication.di.listing

import com.example.nguyenvanmui.myapplication.view.MainActivity
import dagger.Component
import dagger.Subcomponent
import javax.inject.Singleton

/**
 * Created by nguyen.van.mui on 01/02/2018.
 */
@ListingScope
@Subcomponent(modules = arrayOf(ListingModule::class))
interface ListingComponent {
    fun inject(mainActivity: MainActivity)
}