package com.example.nguyenvanmui.myapplication.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.nguyenvanmui.myapplication.view.CustomViewModelFactory
import com.example.nguyenvanmui.myapplication.view.detail.DetailViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by nguyen.van.mui on 13/04/2018.
 */
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    abstract fun bindRepoViewModel(repoViewModel: DetailViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: CustomViewModelFactory): ViewModelProvider.Factory
}