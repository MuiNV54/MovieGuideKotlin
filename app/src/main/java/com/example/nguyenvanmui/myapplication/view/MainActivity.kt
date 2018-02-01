package com.example.nguyenvanmui.myapplication.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.nguyenvanmui.myapplication.MainApplication
import com.example.nguyenvanmui.myapplication.R
import com.example.nguyenvanmui.myapplication.domain.MoviesListingInteractor
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var moviesListingInteractor: MoviesListingInteractor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as MainApplication).createListingComponent().inject(this)
        setContentView(R.layout.activity_main)

        moviesListingInteractor.fetchMovies().subscribe(
                {
                    Log.i("TAG1", it.size.toString())
                }
        )
    }
}
