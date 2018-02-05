package com.example.nguyenvanmui.myapplication.di.detail

import com.example.nguyenvanmui.myapplication.view.detail.DetailFragment
import dagger.Subcomponent

/**
 * Created by nguyen.van.mui on 02/02/2018.
 */
@DetailsScope
@Subcomponent(modules = arrayOf(DetailModule::class))
interface DetailComponent {
    fun inject(detailFragment: DetailFragment)
}