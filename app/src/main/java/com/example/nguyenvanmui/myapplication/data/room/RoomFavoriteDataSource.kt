package com.example.nguyenvanmui.myapplication.data.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.example.nguyenvanmui.myapplication.data.remote.entity.Movie

/**
 * Created by nguyen.van.mui on 05/02/2018.
 */

@Database(
        entities = arrayOf(Movie::class),
        version = 1)
abstract class RoomFavoriteDataSource : RoomDatabase() {
    abstract fun favoriteDao(): RoomFavoriteDao

    companion object {
        @Volatile private var INSTANCE: RoomFavoriteDataSource? = null

        fun getInstance(context: Context): RoomFavoriteDataSource =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: buildPersistentFavorite(context).also { INSTANCE = it }
                }

        fun buildPersistentFavorite(
                context: Context): RoomFavoriteDataSource = Room.databaseBuilder(
                context.applicationContext, RoomFavoriteDataSource::class.java,
                RoomContract.DATABASE_FAVORITES)
                .build()
    }
}