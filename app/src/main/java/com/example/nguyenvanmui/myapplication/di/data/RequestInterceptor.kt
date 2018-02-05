package com.example.nguyenvanmui.myapplication.di.data

import com.example.nguyenvanmui.myapplication.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by nguyen.van.mui on 01/02/2018.
 */

@Singleton
class RequestInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalHttpUrl = original.url()

        val url = originalHttpUrl.newBuilder()
                .addQueryParameter("api_key", BuildConfig.TMDB_API_KEY)
                .build()

        val request = original.newBuilder().url(url).build()
        return chain.proceed(request)
    }
}