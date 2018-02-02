package com.example.nguyenvanmui.myapplication.data.room

import android.content.Context
import android.content.SharedPreferences
import android.text.TextUtils
import com.example.nguyenvanmui.myapplication.data.remote.entity.Movie
import com.squareup.moshi.Moshi
import java.io.IOException
import javax.inject.Inject

/**
 * Created by nguyen.van.mui on 02/02/2018.
 */
class FavoritesStore {
    val PREF_NAME = "FavoritesStore"
    var pref: SharedPreferences

    @Inject
    constructor(context: Context) {
        pref = context.applicationContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun setFavorite(movie: Movie) {
        val editor = pref.edit()
        val moshi = Moshi.Builder().build()
        val jsonAdapter = moshi.adapter(Movie::class.java)
        val movieJson = jsonAdapter.toJson(movie)
        editor.putString(movie.id, movieJson)
        editor.apply()
    }

    fun isFavorite(id: String): Boolean {
        val movieJson = pref.getString(id, null)

        return !TextUtils.isEmpty(movieJson)
    }

    @Throws(IOException::class)
    fun getFavorites(): List<Movie> {
        val allEntries = pref.all
        val movies = ArrayList<Movie>(24)
        val moshi = Moshi.Builder().build()

        for ((key) in allEntries) {
            val movieJson = pref.getString(key, null)

            if (!TextUtils.isEmpty(movieJson)) {
                val jsonAdapter = moshi.adapter(Movie::class.java)

                val movie = jsonAdapter.fromJson(movieJson)
                movies.add(movie)
            } else {
                // Do nothing;
            }
        }
        return movies
    }

    fun unfavorite(id: String) {
        val editor = pref.edit()
        editor.remove(id)
        editor.apply()
    }
}