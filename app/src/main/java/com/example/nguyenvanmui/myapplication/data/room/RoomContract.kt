package com.example.nguyenvanmui.myapplication.data.room

/**
 * Created by nguyen.van.mui on 05/02/2018.
 */
class RoomContract {
    companion object {
        const val DATABASE_FAVORITES = "favorite.db"

        const val TABLE_FAVORITES = "favorites"

        private const val SELECT_COUNT = "SELECT COUNT(*) FROM "
        private const val SELECT_FROM = "SELECT * FROM "

        const val SELECT_FAVORITES_COUNT = SELECT_COUNT + TABLE_FAVORITES
        const val SELECT_FAVORITES = SELECT_FROM + TABLE_FAVORITES
    }
}