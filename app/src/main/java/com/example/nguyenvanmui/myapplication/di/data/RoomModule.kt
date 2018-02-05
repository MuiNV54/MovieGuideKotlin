package com.example.nguyenvanmui.myapplication.di.data

import android.content.Context
import com.example.nguyenvanmui.myapplication.data.room.RoomFavoriteDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by nguyen.van.mui on 05/02/2018.
 */
@Module
class RoomModule {
    @Provides
    @Singleton
    fun provideRoomFavoriteDataSource(
            context: Context) = RoomFavoriteDataSource.buildPersistentFavorite(context)
}