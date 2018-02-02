package com.example.nguyenvanmui.myapplication.di.favorite

import com.example.nguyenvanmui.myapplication.di.AppModule
import com.example.nguyenvanmui.myapplication.domain.FavoritesInteractor
import com.example.nguyenvanmui.myapplication.domain.FavoritesInteractorImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by nguyen.van.mui on 02/02/2018.
 */
@Module(includes = arrayOf(AppModule::class))
class FavoriteModule {
    @Provides
    @Singleton
    fun provideFavouritesInteractor(
            favoritesInteractorImpl: FavoritesInteractorImpl): FavoritesInteractor {
        return favoritesInteractorImpl;
    }
}