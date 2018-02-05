package com.example.nguyenvanmui.myapplication.di.listing

import com.example.nguyenvanmui.myapplication.di.listing.sorting.SortingModule
import com.example.nguyenvanmui.myapplication.view.listing.ListingFragment
import com.example.nguyenvanmui.myapplication.view.listing.sorting.SortingDialogFragment
import dagger.Subcomponent

/**
 * Created by nguyen.van.mui on 01/02/2018.
 */
@Subcomponent(modules = arrayOf(ListingModule::class, SortingModule::class))
interface ListingComponent {
    fun inject(listingFragment: ListingFragment)

    fun inject(sortingDialogFragment: SortingDialogFragment)
}