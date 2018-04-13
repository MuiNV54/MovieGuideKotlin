package com.example.nguyenvanmui.myapplication.domain

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.nguyenvanmui.myapplication.TestOpen
import com.example.nguyenvanmui.myapplication.data.remote.entity.Review
import com.example.nguyenvanmui.myapplication.data.remote.entity.Video
import com.example.nguyenvanmui.myapplication.data.repository.MovieRepository
import io.reactivex.disposables.Disposable
import javax.inject.Inject

/**
 * Created by nguyen.van.mui on 02/02/2018.
 */
@TestOpen
class MovieDetailInteractor @Inject constructor(
        var movieRepository: MovieRepository) {

    val allCompositeDisposable: MutableList<Disposable> = arrayListOf()

    fun getTrailers(id: String): LiveData<List<Video>> {
        val mutableLiveData = MutableLiveData<List<Video>>()
        var disposable = movieRepository.trailers(id).map { return@map it.videos }
                .subscribe({ videos ->
                    mutableLiveData.value = videos
                }, { t: Throwable? ->
                    t!!.printStackTrace()
                })
        allCompositeDisposable.add(disposable)
        return mutableLiveData
    }

    fun getReviews(id: String): LiveData<List<Review>> {
        val mutableLiveData = MutableLiveData<List<Review>>()

        var disposable = movieRepository.reviews(id).map { return@map it.reviews }.subscribe(
                { reviews ->
                    mutableLiveData.value = reviews
                }, { t: Throwable? ->
            t!!.printStackTrace()
        })
        allCompositeDisposable.add(disposable)
        return mutableLiveData
    }
}