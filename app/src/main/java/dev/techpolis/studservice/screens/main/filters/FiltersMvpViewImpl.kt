package dev.techpolis.studservice.screens.main.filters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatSpinner
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import dev.techpolis.studservice.R
import dev.techpolis.studservice.screens.common.mvp.MvpViewObservableBase
import dev.techpolis.studservice.data.model.ServiceTypeEnum

class FiltersMvpViewImpl(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?
) : MvpViewObservableBase<FiltersMvpView.Listener>(), FiltersMvpView {
    override var rootView: View =
        layoutInflater.inflate(R.layout.fragment_main__filters, parent, false)

    private val rgType: RadioGroup = findViewById(R.id.fragment_main__filters__type_rg)
    private val spnGeography: AppCompatSpinner =
        findViewById(R.id.fragment_main__filters__spnGeography)
    private val cgTags: ChipGroup = findViewById(R.id.fragment_main__filters__cgTags)

    private val btnFilter: AppCompatButton = findViewById(R.id.fragment_main__filters__btnFilter)

    init {
        btnFilter.setOnClickListener {
            listeners.forEach {
                it.onFilterBtnClicked(
                    serviceType = getServiceTypeEnum(),
                    geography = spnGeography.selectedItem.toString(),
                    tags = getTagsList()
                )
            }
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
        if (rgType.checkedRadioButtonId == 0)
            ServiceTypeEnum.OFFER
        else
            ServiceTypeEnum.REQUEST

}