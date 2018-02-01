package com.example.nguyenvanmui.myapplication.data.remote.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by nguyen.van.mui on 01/02/2018.
 */
data class Video(var id: String? = null,
        var name: String? = null,
        var site: String? = null,
        @SerializedName("key")
        var videoId: String? = null,
        var size: Int = 0,
        var type: String? = null)
