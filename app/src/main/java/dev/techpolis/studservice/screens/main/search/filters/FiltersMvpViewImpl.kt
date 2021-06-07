package dev.techpolis.studservice.screens.main.search.filters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.Transformation
import androidx.appcompat.widget.*
import androidx.core.view.children
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.tabs.TabLayout
import dev.techpolis.studservice.R
import dev.techpolis.studservice.data.model.ServiceTypeEnum
import dev.techpolis.studservice.screens.common.mvp.MvpViewObservableBase


class FiltersMvpViewImpl(
    override var rootView: View,
    private val layoutInflater: LayoutInflater,
) : MvpViewObservableBase<FiltersMvpView.Listener>(), FiltersMvpView {

    private val tlType: TabLayout = findViewById(R.id.fragment_main__filters_tlType)
    private val etPriceFrom: AppCompatEditText =
        findViewById(R.id.fragment_main__filters__price_from_ev)
    private val etPriceTo: AppCompatEditText =
        findViewById(R.id.fragment_main__filters__price_to_ev)
    private val ibArrow: AppCompatImageButton = findViewById(R.id.fragment_main__filters_arrow_btn)

    private val contentFilter: ViewGroup = findViewById(R.id.fragment_main__filters__content)

    private val cgTags: ChipGroup = findViewById(R.id.fragment_main__filters__cgTags)

    private val etNewChip: AppCompatEditText =
        findViewById(R.id.fragment_main__filters_new_chip_ev)
    private val btnNewChip: AppCompatButton =
        findViewById(R.id.fragment_main__filters__new_chip_btn)

    private val btnApply: AppCompatButton = findViewById(R.id.fragment_main__filters__apply_btn)

    private val selectedColor = getColorStateList(R.color.text_black)
    private val unselectedColor = getColorStateList(R.color.text_gray)

    init {

        ibArrow.setOnClickListener { listeners.forEach { it.onArrowClicked(contentFilter.isVisible) } }

        btnApply.setOnClickListener {
            listeners.forEach {
                it.onApplyButtonClicked(
                    priceFrom = getPriceFrom(),
                    priceTo = getPriceTo()
                )
            }
        }

        tlType.apply {
            addTabWithText("Offer", false)
            addTabWithText("Request", false)
            addTabWithText("None", true)
            addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

                private fun TabLayout.Tab.getTextView(): AppCompatTextView? {
                    val customView: View = customView ?: return null
                    return customView.findViewById(R.id.custom_tab_item__tv)
                }

                override fun onTabSelected(tab: TabLayout.Tab?) {
                    val textView = tab?.getTextView() ?: return
                    textView.makeSelectedStyle()
                    listeners.forEach {
                        it.onTabSelected(getServiceTypeEnum())
                    }
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
            if (newText.isNotEmpty() && isNewTagUnique(newText)) {
                addTag(newText)
            }
        }

        etPriceFrom.doOnTextChanged { text, _, _, _ ->
            listeners.forEach {
                it.onPriceFromFieldChanged(text.toString())
            }
        }

        etPriceTo.doOnTextChanged { text, _, _, _ ->
            listeners.forEach {
                it.onPriceToFieldChanged(text.toString())
            }
        }

    }

    override fun setStateApplyButton(isEnabled: Boolean) {
        btnApply.isEnabled = isEnabled
    }

    private fun getPriceTo(): Int{
        val price = etPriceTo.text.toString()
        return if (price != "") price.toInt() else Int.MAX_VALUE
    }

    private fun getPriceFrom(): Int{
        val price = etPriceFrom.text.toString()
        return if (price != "") price.toInt() else Int.MIN_VALUE
    }

    private fun getTagList(): List<String> {
        val checkedChipsText = mutableListOf<String>()
        for (chip in cgTags.children) {
            checkedChipsText.add((chip as Chip).text.toString())
        }
        return checkedChipsText
    }

    private fun getServiceTypeEnum() =
        when (tlType.selectedTabPosition) {
            0 -> ServiceTypeEnum.OFFER
            1 -> ServiceTypeEnum.REQUEST
            else -> null
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
            if (isSelected) {
                selectTab(newTab)
            }
        }
    }

    override fun showPriceFromFieldError(msgId: Int) {
        etPriceFrom.error = getString(msgId)
    }

    override fun showPriceToFieldError(msgId: Int) {
        etPriceTo.error = getString(msgId)
    }

    override fun hidePriceFromFieldError() {
        etPriceFrom.error = null
    }

    override fun hidePriceToFieldError() {
        etPriceTo.error = null
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

    override fun setTagList(tagList: List<String>) {
        tagList.forEach {
            addChip(it)
        }
    }

    override fun setTypeTab(type: ServiceTypeEnum?) {
        tlType.selectTab(
            tlType.getTabAt(
                when (type) {
                    ServiceTypeEnum.OFFER -> 0
                    ServiceTypeEnum.REQUEST -> 1
                    else -> 2
                }
            )
        )
    }

    private fun AppCompatTextView.makeSelectedStyle() {
        textSize = 19f
        setTextColor(selectedColor)
        setPadding(0, 0, 0, 0)
    }

    private fun AppCompatTextView.makeUnselectedStyle() {
        textSize = 18f
        setTextColor(unselectedColor)
        setPadding(0, 0, 0, 0)
    }

    override fun setContentVisible(isContentVisible: Boolean, withAnimation: Boolean) {
        contentFilter.apply {
            if (isContentVisible) {
                if (withAnimation) expand() else isVisible = true
            } else {
                if (withAnimation) collapse() else isVisible = false
            }
        }
        ibArrow.setBackgroundResource(if (isContentVisible) R.drawable.ic_arrow_up else R.drawable.ic_arrow_down)
    }

    private fun View.expand() {
        val matchParentMeasureSpec =
            View.MeasureSpec.makeMeasureSpec((parent as View).width, View.MeasureSpec.EXACTLY)
        val wrapContentMeasureSpec =
            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        measure(matchParentMeasureSpec, wrapContentMeasureSpec)
        val targetHeight = measuredHeight
        layoutParams.height = 1
        visibility = View.VISIBLE
        val a: Animation = object : Animation() {
            override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
                layoutParams.height =
                    if (interpolatedTime == 1f) ViewGroup.LayoutParams.WRAP_CONTENT else (targetHeight * interpolatedTime).toInt()
                requestLayout()
                alpha = 1f * interpolatedTime
            }

            override fun willChangeBounds(): Boolean {
                return true
            }
        }
        a.duration = 800
       // a.duration = (targetHeight / context.resources.displayMetrics.density).toLong()
        startAnimation(a)
    }

    private fun View.collapse() {
        val initialHeight = measuredHeight
        val a: Animation = object : Animation() {
            override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
                if (interpolatedTime == 1f) {
                    visibility = View.GONE
                } else {
                    layoutParams.height =
                        initialHeight - (initialHeight * interpolatedTime).toInt()
                    alpha = 1f - (1f * interpolatedTime)
                    requestLayout()
                }
            }

            override fun willChangeBounds(): Boolean {
                return true
            }
        }
        a.duration = 600
        //a.duration = (initialHeight / context.resources.displayMetrics.density).toLong()
        startAnimation(a)
    }

}