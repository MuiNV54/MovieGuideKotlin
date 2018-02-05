package com.example.nguyenvanmui.myapplication.view

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import com.example.nguyenvanmui.myapplication.Constants
import com.example.nguyenvanmui.myapplication.R
import com.example.nguyenvanmui.myapplication.data.remote.entity.Movie
import com.example.nguyenvanmui.myapplication.view.detail.DetailFragment
import com.example.nguyenvanmui.myapplication.view.detail.DetailsActivity
import com.example.nguyenvanmui.myapplication.view.listing.ListingFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ListingFragment.Callback {

    val DETAILS_FRAGMENT = "DetailsFragment"
    private var twoPaneMode: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setToolbar()

        if (movie_details_container != null) {
            twoPaneMode = true

            if (savedInstanceState == null) {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.movie_details_container, DetailFragment())
                        .commit()
            }
        } else {
            twoPaneMode = false
        }
    }

    private fun setToolbar() {
        setSupportActionBar(listing_toolbar)

        if (supportActionBar != null) {
            supportActionBar?.setTitle(R.string.movie_guide)
            supportActionBar?.setDisplayShowTitleEnabled(true)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onMoviesLoaded(movie: Movie) {
        if (twoPaneMode) {
            loadMovieFragment(movie)
        } else {
            // Do not load in single pane view
        }
    }

    override fun onMovieClicked(movie: Movie) {
        if (twoPaneMode) {
            loadMovieFragment(movie)
        } else {
            startMovieActivity(movie)
        }
    }

    private fun startMovieActivity(movie: Movie) {
        val intent = Intent(this, DetailsActivity::class.java)
        val extras = Bundle()
        extras.putParcelable(Constants.MOVIE, movie)
        intent.putExtras(extras)
        startActivity(intent)
    }

    private fun loadMovieFragment(movie: Movie) {
        val movieDetailsFragment = DetailFragment.getInstance(movie)
        supportFragmentManager.beginTransaction()
                .replace(R.id.movie_details_container, movieDetailsFragment, DETAILS_FRAGMENT)
                .commit()
    }

}
