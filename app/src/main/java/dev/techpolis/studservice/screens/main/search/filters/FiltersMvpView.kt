package dev.techpolis.studservice.screens.main.search.filters

import dev.techpolis.studservice.screens.common.mvp.MvpViewObservable
import dev.techpolis.studservice.screens.common.nav.BackPressedListener
import dev.techpolis.studservice.data.model.ServiceTypeEnum

interface FiltersMvpView : MvpViewObservable<FiltersMvpView.Listener> {
    interface Listener : BackPressedListener {
        fun onApplyButtonClicked(priceFrom: Int, priceTo: Int, )
        fun onArrowClicked(isContentVisible: Boolean)
        fun onChipDeleted(tagText: String)
        fun onChipAdded(tagText: String)
        fun onTabSelected(serviceTypeEnum: ServiceTypeEnum?)
    }

    fun setContentVisible(isContentVisible: Boolean, withAnimation: Boolean = true)
    fun setTagList(tagList: List<String>)
    fun setTypeTab(type: ServiceTypeEnum?)

}