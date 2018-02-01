package com.example.nguyenvanmui.myapplication.data.remote.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by nguyen.van.mui on 01/02/2018.
 */
data class VideosResponse(@SerializedName("results")
var movies: List<Video>? = null)