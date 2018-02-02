package com.example.nguyenvanmui.myapplication.domain

import com.example.nguyenvanmui.myapplication.data.remote.entity.Review
import com.example.nguyenvanmui.myapplication.data.remote.entity.Video
import io.reactivex.Observable

/**
 * Created by nguyen.van.mui on 02/02/2018.
 */
interface MovieDetailInteractor {
    fun getTrailers(id: String): Observable<List<Video>>

    fun getReviews(id: String): Observable<List<Review>>
}