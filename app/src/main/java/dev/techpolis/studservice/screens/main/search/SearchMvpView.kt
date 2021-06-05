package dev.techpolis.studservice.screens.main.search

import dev.techpolis.studservice.data.entities.ServiceEntity
import dev.techpolis.studservice.screens.common.mvp.MvpViewObservable
import dev.techpolis.studservice.screens.common.nav.BackPressedListener
import dev.techpolis.studservice.screens.main.search.filters.FiltersMvpView

interface SearchMvpView: MvpViewObservable<SearchMvpView.Listener> {
    interface Listener : BackPressedListener {
        fun onSearchFieldTextChanged(text: String)
        fun onBackIconClicked()
        fun onClearIconClicked()
        fun onServiceClicked(service: ServiceEntity)
    }

    fun setClearIconVisibility(isVisible: Boolean)
    fun clearSearchFieldText()
    fun getFiltersMvpView(): FiltersMvpView
    fun bindData(listOffers: List<ServiceEntity>)
}