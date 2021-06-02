package dev.techpolis.studservice.screens.main.user_services.new

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.appcompat.widget.*
import androidx.core.view.children
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.tabs.TabLayout
import dev.techpolis.studservice.R
import dev.techpolis.studservice.data.model.DeadlineDate
import dev.techpolis.studservice.data.model.ServiceTypeEnum
import dev.techpolis.studservice.screens.common.mvp.MvpViewObservableBase

class NewServiceMvpViewImpl(
    private val layoutInflater: LayoutInflater,
    parent: ViewGroup?
) : MvpViewObservableBase<NewServiceMvpView.Listener>(), NewServiceMvpView {
    override var rootView: View =
        layoutInflater.inflate(R.layout.fragment_main__user_services__new_service, parent, false)

    private val etTitle: AppCompatEditText =
        findViewById(R.id.fragment_main__user_services__new_service_title_ev)
    private val etDescription: AppCompatEditText =
        findViewById(R.id.fragment_main__user_services__new_service_desc_ev)
    private val tlType: TabLayout =
        findViewById(R.id.fragment_main__user_services__new_service_tab_layout)
    private val etPrice: AppCompatEditText =
        findViewById(R.id.fragment_main__user_services__new_service_price_ev)
    private val cgTags: ChipGroup =
        findViewById(R.id.fragment_main__user_services__new_service__cgTags)
    private val btnCreate: AppCompatButton =
        findViewById(R.id.fragment_main__user_services__new_service__create_btn)
    private val tvDate: AppCompatTextView =
        findViewById(R.id.fragment_main__user_services__new_service_date_tv)
    private val btnBack: AppCompatImageButton =
        findViewById(R.id.fragment_main__user_services__new_service__back_btn)
    private val etNewChip: AppCompatEditText =
        findViewById(R.id.fragment_main__user_services__new_service_new_chip_ev)
    private val btnNewChip: AppCompatButton =
        findViewById(R.id.fragment_main__user_services__new_service__new_chip_btn)

    private val selectedColor = getColorStateList(R.color.text_black)
    private val unselectedColor = getColorStateList(R.color.text_gray)

    init {
        tvDate.setOnClickListener {
            listeners.forEach {
                it.getToDatePicker()
            }
        }

        btnCreate.setOnClickListener {
            listeners.forEach {
                it.onCreateServiceBtnClicked(
                    title = etTitle.text.toString(),
                    description = etDescription.text.toString(),
                    price = getPrice(),
//                    serviceType = getServiceTypeEnum(),
//                    deadline = "$300",
//                    tags = getTagsList()
                )
            }
        }

        btnBack.setOnClickListener {
            listeners.forEach {
                it.onBackBtnClicked()
            }
        }

        btnNewChip.setOnClickListener {
            val newTag = etNewChip.text.toString()
            if (newTag.isNotEmpty() && isNewTagUnique(newTag)) {
                addTag(newTag)
            }
        }

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
                    listeners.forEach { it.onTypeSelected(getServiceTypeEnum()) }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {

                    val textView = tab?.getTextView() ?: return
                    textView.makeUnselectedStyle()
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {}

            })

        }
    }

    override fun setDate(deadline: DeadlineDate) {
        tvDate.text = deadline.toString()
    }

    override fun setTagList(tagList: List<String>) {
        tagList.forEach {
            addChip(it)
        }
    }

    override fun setTypeTab(type: ServiceTypeEnum) {
        tlType.selectTab(
            tlType.getTabAt(
                when (type) {
                    ServiceTypeEnum.OFFER -> 0
                    ServiceTypeEnum.REQUEST -> 1
                }
            )
        )
    }

    private fun TabLayout.addTabWithText(text: String, isSelected: Boolean) {
        val tabContainer =
            layoutInflater.inflate(R.layout.custom_tab_item, this, false) as ViewGroup
        val textView =
            tabContainer.findViewById<AppCompatTextView>(R.id.custom_tab_item__tv)
        val newTab = newTab()
        textView.text = text
        newTab.customView = tabContainer
        if (isSelected) {
            textView.makeSelectedStyle()
        } else {
            textView.makeUnselectedStyle()
        }
        addTab(newTab)
    }

    private fun isNewTagUnique(text: String): Boolean {
        for (chip in cgTags.children) {
            if ((chip as Chip).text == text) {
                return false
            }
        }
        return true
    }

    private fun addTag(tagText: String) {
        val newChip = layoutInflater.inflate(R.layout.custom_chip_closable, cgTags, false) as Chip
        newChip.apply {
            text = tagText
            setOnCloseIconClickListener {
                listeners.forEach { it.onChipDeleted(tagText) }
                cgTags.removeView(newChip)
            }
        }
        listeners.forEach { it.onChipAdded(tagText) }
        cgTags.addView(newChip)
        etNewChip.text?.clear()
    }

    private fun addChip(tagText: String) {
        val newChip = layoutInflater.inflate(R.layout.custom_chip_closable, cgTags, false) as Chip
        newChip.apply {
            text = tagText
            setOnCloseIconClickListener {
                listeners.forEach { it.onChipDeleted(tagText) }
                cgTags.removeView(newChip)
            }
        }
        cgTags.addView(newChip)
        etNewChip.text?.clear()
    }

    private fun getPrice(): Int =
        if (etPrice.text!!.isEmpty()) 0 else etPrice.text.toString().toInt()

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

    private fun AppCompatTextView.makeSelectedStyle() {
        textSize = 20f
        setTextColor(selectedColor)
        setPadding(0, 0, 0, 0)
    }

    private fun AppCompatTextView.makeUnselectedStyle() {
        textSize = 18f
        setTextColor(unselectedColor)
        setPadding(0, 0, 0, 0)
    }

}



