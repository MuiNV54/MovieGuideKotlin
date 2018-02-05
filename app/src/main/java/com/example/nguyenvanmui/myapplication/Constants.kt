package com.example.nguyenvanmui.myapplication

import com.example.nguyenvanmui.myapplication.data.remote.entity.Video

/**
 * Created by nguyen.van.mui on 02/02/2018.
 */
object Constants {
    val MOVIE = "movie"
    val EMPTY = ""
    val SITE_YOUTUBE = "YouTube"

    val BASE_POSTER_PATH = "http://image.tmdb.org/t/p/w342"
    val BASR_BACKDROP_PATH = "http://image.tmdb.org/t/p/w780"
    internal val YOUTUBE_VIDEO_URL = "http://www.youtube.com/watch?v=%1\$s"
    internal val YOUTUBE_THUMBNAIL_URL = "http://img.youtube.com/vi/%1\$s/0.jpg"

    fun getPosterPath(posterPath: String): String {
        return BASE_POSTER_PATH + posterPath
    }

    fun getBackdropPath(backdropPath: String): String {
        return BASR_BACKDROP_PATH + backdropPath
    }
}