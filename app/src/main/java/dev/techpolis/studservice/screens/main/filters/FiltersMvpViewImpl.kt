package dev.techpolis.studservice.screens.main.filters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.RadioGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatSpinner
import androidx.core.widget.doOnTextChanged
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import dev.techpolis.studservice.R
import dev.techpolis.studservice.screens.common.mvp.MvpViewObservableBase

class FiltersMvpViewImpl(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?
) : MvpViewObservableBase<FiltersMvpView.Listener>(), FiltersMvpView {
    override var rootView: View =
        layoutInflater.inflate(R.layout.fragment_main__filters, parent, false)

    private val rgType: RadioGroup = findViewById(R.id.fragment_main__filters__type_rg)
    private val spnGeography: AppCompatSpinner = findViewById(R.id.fragment_main__filters__spnGeography)
    private val cgTags: ChipGroup = findViewById(R.id.fragment_main__filters__cgTags)

    private val btnFilter: AppCompatButton = findViewById(R.id.fragment_main__filters__btnFilter)

    init {
        cgTags.setOnCheckedChangeListener { chipGroup: ChipGroup, position: Int ->
            listeners.forEach {
                it.onTagsCheckedChanged(chipGroup, position)
            }
        }

        rgType.setOnCheckedChangeListener { radioGroup: RadioGroup, _ ->
            listeners.forEach {
                it.onServiceTypeChanged(radioGroup)
            }
        }

        spnGeography.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                listeners.forEach {
                    it.onGeographyChanged(parent?.getItemAtPosition(position).toString())
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        btnFilter.setOnClickListener {
            listeners.forEach {
                it.onFilterBtnClicked()
            }
        }
    }

}