package com.example.nguyenvanmui.myapplication.domain

import com.example.nguyenvanmui.myapplication.data.remote.entity.Review
import com.example.nguyenvanmui.myapplication.data.remote.entity.Video
import com.example.nguyenvanmui.myapplication.data.repository.MovieRepository
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by nguyen.van.mui on 02/02/2018.
 */
class MovieDetailInteractorImpl @Inject constructor(
        var movieRepository: MovieRepository) : MovieDetailInteractor {

    override fun getTrailers(id: String): Observable<List<Video>> {
        return movieRepository.trailers(id).map { return@map it.videos }
    }

    override fun getReviews(id: String): Observable<List<Review>> {
        return movieRepository.reviews(id).map { return@map it.reviews }
    }
}