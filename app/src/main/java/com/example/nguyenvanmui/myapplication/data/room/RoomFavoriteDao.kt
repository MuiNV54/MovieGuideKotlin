package com.example.nguyenvanmui.myapplication.data.room

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.nguyenvanmui.myapplication.data.remote.entity.Movie
import io.reactivex.Flowable
import io.reactivex.Maybe
import org.jetbrains.annotations.NotNull

/**
 * Created by nguyen.van.mui on 05/02/2018.
 */

@Dao
interface RoomFavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(@NotNull movie: Movie)

    @Query(RoomContract.SELECT_FAVORITES)
    fun getAllFavorites(): Maybe<List<Movie>>

    @Query(RoomContract.SELECT_FAVORITES + " WHERE id = :id LIMIT 1")
    fun getFavorite(id: String): Maybe<Movie>

    @Query("DELETE FROM favorites WHERE id = :id")
    fun deleteFavorite(id: String)
}