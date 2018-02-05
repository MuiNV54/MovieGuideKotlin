package com.example.nguyenvanmui.myapplication.data.room

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

/**
 * Created by nguyen.van.mui on 02/02/2018.
 */
class SortingOptionStore {
    var pref: SharedPreferences
    private val SELECTED_OPTION = "selectedOption"
    private val PREF_NAME = "SortingOptionStore"

    @Inject
    constructor(context: Context) {
        pref = context.applicationContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun setSelectedOption(sortType: SortType) {
        val editor = pref.edit()
        editor.putInt(SELECTED_OPTION, sortType.value)
        editor.apply()
    }

    fun getSelectedOption(): Int {
        return pref.getInt(SELECTED_OPTION, 0)
    }
}