package dev.techpolis.studservice.screens.main.userservices.newservice

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.appcompat.widget.*
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.tabs.TabItem
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dev.techpolis.studservice.R
import dev.techpolis.studservice.data.model.ServiceTypeEnum
import dev.techpolis.studservice.screens.common.mvp.MvpViewObservableBase

class NewServiceMvpViewImpl(
    layoutInflater: LayoutInflater,
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
    private val etDeadline: AppCompatEditText =
        findViewById(R.id.fragment_main__user_services__new_service_deadlines_ev)
    private val btnBack: AppCompatImageButton =
        findViewById(R.id.fragment_main__user_services__new_service__back_btn)

    private val selectedColor = getColorStateList(R.color.text_black)
    private val unselectedColor = getColorStateList(R.color.text_gray)

    init {
        btnCreate.setOnClickListener {
            listeners.forEach {
                it.onCreateServiceBtnClicked(
                    title = etTitle.text.toString(),
                    desc = etDescription.text.toString(),
                    price = getPrice(),
                    serviceType = getServiceTypeEnum(),
                    deadline = getDeadline(),
                    tags = getTagsList()
                )
            }
        }

        btnBack.setOnClickListener {
            listeners.forEach {
                it.onBackBtnClicked()
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
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {

                    val textView = tab?.getTextView() ?: return
                    textView.makeUnselectedStyle()
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {}

            })

    }
}

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

private fun getPrice(): Double =
    if (etPrice.text!!.isEmpty()) 0.0
    else etPrice.text.toString().toDouble()

private fun getDeadline(): String = etDeadline.text.toString()

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



