package com.example.nguyenvanmui.myapplication.data.room

/**
 * Created by nguyen.van.mui on 02/02/2018.
 */
enum class SortType {
    MOST_POPULAR(0), HIGHEST_RATED(1), FAVORITES(2);

    val value: Int

    constructor(value: Int) {
        this.value = value
    }
}