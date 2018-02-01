package com.example.nguyenvanmui.myapplication.di.network

import com.example.nguyenvanmui.myapplication.BuildConfig
import com.example.nguyenvanmui.myapplication.data.remote.TmdbWebService
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by nguyen.van.mui on 01/02/2018.
 */
@Module
class NetworkModule {
    val CONNECT_TIMEOUT_IN_MS = 30000

    @Provides
    @Singleton
    internal fun requestInterceptor(interceptor: RequestInterceptor): Interceptor {
        return interceptor
    }

    @Provides
    @Singleton
    internal fun provideOkHttpClient(requestInterceptor: RequestInterceptor): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return okhttp3.OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT_IN_MS.toLong(), TimeUnit.MILLISECONDS)
                .addInterceptor(loggingInterceptor)
                .addInterceptor(requestInterceptor)
                .build()
    }

    @Singleton
    @Provides
    internal fun retrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BuildConfig.TMDB_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()
    }

    @Singleton
    @Provides
    internal fun webService(retrofit: Retrofit): TmdbWebService {
        return retrofit.create<TmdbWebService>(TmdbWebService::class.java)
    }
}