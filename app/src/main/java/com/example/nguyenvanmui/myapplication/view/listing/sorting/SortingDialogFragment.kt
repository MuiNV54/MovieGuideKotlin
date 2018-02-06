package com.example.nguyenvanmui.myapplication.view.listing.sorting

import android.app.Dialog
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.widget.RadioButton
import android.widget.RadioGroup
import com.example.nguyenvanmui.myapplication.R
import com.example.nguyenvanmui.myapplication.data.room.SortType

/**
 * Created by nguyen.van.mui on 05/02/2018.
 */
class SortingDialogFragment : DialogFragment(), SortingDialogView, RadioGroup.OnCheckedChangeListener {
    lateinit var sortingViewModel: SortingViewModel

    lateinit var sortingGroup: RadioGroup
    lateinit var mostPopular: RadioButton
    lateinit var highestRated: RadioButton
    lateinit var favorites: RadioButton

    companion object {
        lateinit var optionChangeCallback: OptionChangeCallback

        fun newInstance(optionChangeCallback: OptionChangeCallback): SortingDialogFragment {
            this.optionChangeCallback = optionChangeCallback
            return SortingDialogFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setRetainInstance(true)
        initViewModel()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = LayoutInflater.from(activity)
        val dialogView = inflater.inflate(R.layout.sorting_options, null).apply {
            sortingGroup = findViewById(R.id.sorting_group)
            mostPopular = findViewById(R.id.most_popular)
            highestRated = findViewById(R.id.highest_rated)
            favorites = findViewById(R.id.favorites)
        }

        val dialog = Dialog(getActivity())
        dialog.setContentView(dialogView)
        dialog.setTitle(R.string.sort_by)
        dialog.show()
        return dialog
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViews()
    }

    private fun initViewModel() {
        sortingViewModel = ViewModelProviders.of(this).get(SortingViewModel::class.java)
        sortingViewModel.let { lifecycle.addObserver(it) }
    }

    private fun initViews() {
        when (sortingViewModel.setLastSavedOption()) {
            SortType.MOST_POPULAR.value ->
                setPopularChecked()
            SortType.HIGHEST_RATED.value ->
                setHighestRatedChecked()
            else ->
                setFavoritesChecked()
        }
        sortingGroup.setOnCheckedChangeListener(this)
    }

    override fun setPopularChecked() {
        mostPopular.isChecked = true
    }

    override fun setHighestRatedChecked() {
        highestRated.isChecked = true
    }

    override fun setFavoritesChecked() {
        favorites.isChecked = true
    }

    override fun onCheckedChanged(radioGroup: RadioGroup, checkedId: Int) {
        when (checkedId) {
            R.id.most_popular -> {
                sortingViewModel.onPopularMoviesSelected()
                optionChangeCallback.onOptionChange()
                dismissDialog()
            }

            R.id.highest_rated -> {
                sortingViewModel.onHighestRatedMoviesSelected()
                optionChangeCallback.onOptionChange()
                dismissDialog()
            }

            R.id.favorites -> {
                sortingViewModel.onFavoritesSelected()
                optionChangeCallback.onOptionChange()
                dismissDialog()
            }
        }
    }

    override fun dismissDialog() {
        dismiss()
    }

    interface OptionChangeCallback {
        fun onOptionChange()
    }
}