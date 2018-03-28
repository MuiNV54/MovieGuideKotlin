package com.example.nguyenvanmui.myapplication.data.room

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.example.nguyenvanmui.myapplication.data.remote.entity.Movie
import com.example.nguyenvanmui.myapplication.data.room.RoomFavoriteDataSource
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by nguyen.van.mui on 28/03/2018.
 */
@RunWith(AndroidJUnit4::class)
class RoomFavoriteDaoTest {
    private lateinit var database: RoomFavoriteDataSource

    @Before
    fun initDb() {
        database = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                RoomFavoriteDataSource::class.java)
                // allowing main thread queries, just for testing
                .allowMainThreadQueries()
                .build()
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun getFavoriteWhenNoInserted() {
        database.favoriteDao().getAllFavorites()
                .test()
                .assertValue {
                    it.size == 0
                }

        database.favoriteDao().getFavorite("123")
                .test()
                .assertNoValues()
    }

    @Test
    fun insertAndGetFavorite() {
        database.favoriteDao().insert(movie1)
        database.favoriteDao().insert(movie2)

        database.favoriteDao().getAllFavorites()
                .test()
                .assertValue { it.size == 2 }

        database.favoriteDao().getFavorite(movie1.id)
                .test()
                .assertValue {
                    it.id == movie1.id &&
                            it.voteAverage == movie1.voteAverage
                }

        database.favoriteDao().getFavorite(movie2.id)
                .test()
                .assertValue {
                    it.id == movie2.id &&
                            it.voteAverage == movie2.voteAverage
                }
    }

    @Test
    fun deleteFavorite() {
        database.favoriteDao().insert(movie1)
        database.favoriteDao().getAllFavorites()
                .test()
                .assertValue {
                    it.size == 1
                }
        database.favoriteDao().deleteFavorite(movie1.id)
        database.favoriteDao().getAllFavorites().test().assertValue { it.size == 0 }
    }

    companion object {
        private val movie1 = Movie("123", voteAverage = 4.5)
        private val movie2 = Movie("124", voteAverage = 5.0)
    }
}