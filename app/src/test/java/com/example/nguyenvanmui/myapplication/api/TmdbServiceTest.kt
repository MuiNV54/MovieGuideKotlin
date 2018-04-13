package com.example.nguyenvanmui.myapplication.api

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.nguyenvanmui.myapplication.data.remote.TmdbWebService
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import okio.Okio
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.nio.charset.StandardCharsets
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

/**
 * Created by nguyen.van.mui on 13/04/2018.
 */
@RunWith(JUnit4::class)
class TmdbServiceTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    lateinit var service: TmdbWebService

    lateinit var mockWebServer: MockWebServer

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        service = Retrofit.Builder()
                .baseUrl(mockWebServer.url("/"))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create<TmdbWebService>(TmdbWebService::class.java!!)
    }

    @After
    @Throws(IOException::class)
    fun stopService() {
        mockWebServer.shutdown()
    }

    @Test
    fun testGetTrailers() {
        enqueueResponse("videos.json")
        assertEquals(2 + 2, 4)
        var response = service.trailers("123")

        var request: RecordedRequest = mockWebServer.takeRequest()
        assertEquals(request.path, "/3/movie/123/videos")

        assertNotNull(response)
        response.test().assertValue {
            it.videos?.size == 5
            it.videos?.get(0)?.id == "571cb2c0c3a36843150006ed"
        }
    }

    @Throws(IOException::class)
    private fun enqueueResponse(fileName: String, headers: Map<String, String>? = null) {
        val inputStream = javaClass.classLoader
                .getResourceAsStream("api-response/" + fileName)
        val source = Okio.buffer(Okio.source(inputStream))
        val mockResponse = MockResponse()
        if (headers != null) {
            for ((key, value) in headers) {
                mockResponse.addHeader(key, value)
            }
        }
        mockWebServer.enqueue(mockResponse
                .setBody(source.readString(StandardCharsets.UTF_8)))
    }
}