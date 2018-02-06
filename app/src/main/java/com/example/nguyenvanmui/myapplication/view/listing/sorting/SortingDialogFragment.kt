package com.example.nguyenvanmui.myapplication.view.listing.sorting

import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.widget.RadioButton
import android.widget.RadioGroup
import com.example.nguyenvanmui.myapplication.MainApplication
import com.example.nguyenvanmui.myapplication.R
import javax.inject.Inject

/**
 * Created by nguyen.van.mui on 05/02/2018.
 */
class SortingDialogFragment : DialogFragment(), SortingDialogView, RadioGroup.OnCheckedChangeListener {
    @Inject
    lateinit var sortingDialogPresenter: SortingPresenter

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
        MainApplication.appComponent.inject(this)
        sortingDialogPresenter.setViewPresenter(this)
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

    private fun initViews() {
        sortingDialogPresenter.setLastSavedOption()
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
                sortingDialogPresenter.onPopularMoviesSelected()
                optionChangeCallback.onOptionChange()
            }

            R.id.highest_rated -> {
                sortingDialogPresenter.onHighestRatedMoviesSelected()
                optionChangeCallback.onOptionChange()
            }

            R.id.favorites -> {
                sortingDialogPresenter.onFavoritesSelected()
                optionChangeCallback.onOptionChange()
            }
        }
    }

    override fun dismissDialog() {
        dismiss()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        sortingDialogPresenter.destroy()
    }

    interface OptionChangeCallback {
        fun onOptionChange()
    }
}