package dev.techpolis.studservice.screens.main.filters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatSpinner
import androidx.core.widget.doAfterTextChanged
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.tabs.TabLayout
import dev.techpolis.studservice.R
import dev.techpolis.studservice.data.model.ServiceTypeEnum
import dev.techpolis.studservice.screens.common.mvp.MvpViewObservableBase

class FiltersMvpViewImpl(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?
) : MvpViewObservableBase<FiltersMvpView.Listener>(), FiltersMvpView {
    override var rootView: View =
        layoutInflater.inflate(R.layout.fragment_main__filters, parent, false)

    private val tlType: TabLayout = findViewById(R.id.fragment_main__filters_tlType)
    private val etPriceFrom: AppCompatEditText = findViewById(R.id.fragment_main__filters__price_from_ev)
    private val etPriceTo: AppCompatEditText = findViewById(R.id.fragment_main__filters__price_to_ev)

    private val spnLocation: AppCompatSpinner =
        findViewById(R.id.fragment_main__filters__location_spn)
    private val cgTags: ChipGroup = findViewById(R.id.fragment_main__filters__cgTags)

    init {
        tlType.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                onFiltersChanged()
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) { }
            override fun onTabReselected(tab: TabLayout.Tab?) { }
        })

        etPriceFrom.doAfterTextChanged {
            onFiltersChanged()
        }

        etPriceTo.doAfterTextChanged {
            onFiltersChanged()
        }

        spnLocation.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                onFiltersChanged()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) { }
        }

        cgTags.setOnCheckedChangeListener { _, _ -> onFiltersChanged() }

    }

    private fun onFiltersChanged() {
        listeners.forEach {
            it.onFiltersChanged(
                serviceType = getServiceTypeEnum(),
                location = spnLocation.selectedItem.toString(),
                tags = getTagsList()
            )
        }
    }

    private fun getTagsList(): List<String> {
        val checkedChipsText = mutableListOf<String>()
        cgTags.checkedChipIds.forEach {
            val chip = cgTags.findViewById<Chip>(it).text.toString()
            checkedChipsText.add(chip)
        }
        return checkedChipsText
    }

    private fun getServiceTypeEnum() =
        if (tlType.selectedTabPosition == 0)
            ServiceTypeEnum.OFFER
        else
            ServiceTypeEnum.REQUEST

}