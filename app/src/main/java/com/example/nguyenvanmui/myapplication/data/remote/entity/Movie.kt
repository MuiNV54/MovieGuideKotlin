package com.example.nguyenvanmui.myapplication.data.remote.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by nguyen.van.mui on 01/02/2018.
 */
data class Movie(
        var id: String? = null,
        var overview: String? = null,
        @SerializedName("release_date")
        var releaseDate: String? = null,
        @SerializedName("poster_path")
        var posterPath: String? = null,
        @SerializedName("backdrop_path")
        var backdropPath: String? = null,
        var title: String? = null,
        @SerializedName("vote_average")
        var voteAverage: Double = 0.toDouble()
)