package dev.techpolis.studservice.screens.main.filters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatSpinner
import androidx.appcompat.widget.AppCompatTextView
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

    private val etNewChip: AppCompatEditText =
        findViewById(R.id.fragment_main__filters_new_chip_ev)
    private val btnNewChip: AppCompatButton =
        findViewById(R.id.fragment_main__filters__new_chip_btn)

    private val selectedColor = getColorStateList(R.color.text_black)
    private val unselectedColor = getColorStateList(R.color.text_gray)

    init {
        tlType.apply {
            addTabWithText(getString(R.string.offers_tab_title), true)
            addTabWithText(getString(R.string.requests_tab_title), false)

            addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

                private fun TabLayout.Tab.getTextView(): AppCompatTextView? {
                    val customView: View = customView ?: return null
                    return customView.findViewById(R.id.custom_tab_item__tv)
                }

                override fun onTabSelected(tab: TabLayout.Tab?) {
                    val textView = tab?.getTextView() ?: return
                    textView.makeSelectedStyle()
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {

                    val textView = tab?.getTextView() ?: return
                    textView.makeUnselectedStyle()
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {}

            })

        }

        btnNewChip.setOnClickListener {
            val newText = etNewChip.text.toString()
            if (newText.isNotEmpty()) {
                val newChip = layoutInflater.inflate(R.layout.custom_chip, cgTags, false) as Chip
                newChip.text = newText
                cgTags.addView(newChip)
                etNewChip.text?.clear()
            }
        }

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

    private fun TabLayout.addTabWithText(text: String, isSelected: Boolean) {
        val tabContainer = LayoutInflater.from(context)
            .inflate(R.layout.custom_tab_item, this, false) as ViewGroup?
        if (tabContainer != null) {
            val textView =
                tabContainer.findViewById<AppCompatTextView>(R.id.custom_tab_item__tv)
            val newTab = newTab()
            if (isSelected) {
                textView.makeSelectedStyle()
            } else {
                textView.makeUnselectedStyle()
            }
            textView.text = text
            newTab.customView = tabContainer
            addTab(newTab)
        }
    }

    private fun AppCompatTextView.makeSelectedStyle() {
        textSize = 28f
        setTextColor(selectedColor)
        setPadding(0, 0, 0, 0)
    }

    private fun AppCompatTextView.makeUnselectedStyle() {
        textSize = 18f
        setTextColor(unselectedColor)
        setPadding(0, 0, 0, 0)
    }

}