package com.example.nguyenvanmui.myapplication.view.detail

import com.example.nguyenvanmui.myapplication.data.remote.entity.Movie
import com.example.nguyenvanmui.myapplication.data.remote.entity.Review
import com.example.nguyenvanmui.myapplication.data.remote.entity.Video

/**
 * Created by nguyen.van.mui on 02/02/2018.
 */
interface DetailView {
    fun showDetails(movie: Movie)

    fun showTrailers(trailers: List<Video>)

    fun showReviews(reviews: List<Review>)

    fun showFavorited()

    fun showUnFavorited()
}