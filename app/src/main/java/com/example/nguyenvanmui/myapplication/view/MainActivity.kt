package com.example.nguyenvanmui.myapplication.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.nguyenvanmui.myapplication.MainApplication
import com.example.nguyenvanmui.myapplication.R
import com.example.nguyenvanmui.myapplication.data.remote.entity.Movie
import com.example.nguyenvanmui.myapplication.data.room.SortType
import com.example.nguyenvanmui.myapplication.domain.FavoritesInteractor
import com.example.nguyenvanmui.myapplication.domain.MoviesListingInteractor
import com.example.nguyenvanmui.myapplication.domain.SortingInteractor
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var moviesListingInteractor: MoviesListingInteractor
    @Inject
    lateinit var favoritesInteractor: FavoritesInteractor
    @Inject
    lateinit var sortingInteractor: SortingInteractor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as MainApplication).createListingComponent().inject(this)
        setContentView(R.layout.activity_main)

        moviesListingInteractor.fetchMovies().subscribe(
                {
                    Log.i("TAG1", it.size.toString())
                }
        )

        favoritesInteractor.setFavorite(Movie("123"))
        Log.i("TAG1", favoritesInteractor.isFavorite("123").toString())

        sortingInteractor.setSortingOption(SortType.FAVORITES)
        Log.i("TAG1", sortingInteractor.getSelectedSortingOption().toString())
    }
}
