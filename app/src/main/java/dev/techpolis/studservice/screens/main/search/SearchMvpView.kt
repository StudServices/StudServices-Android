package dev.techpolis.studservice.screens.main.search

import dev.techpolis.studservice.screens.common.mvp.MvpViewObservable
import dev.techpolis.studservice.screens.common.nav.BackPressedListener
import dev.techpolis.studservice.data.model.ServiceTypeEnum

interface SearchMvpView: MvpViewObservable<SearchMvpView.Listener> {
    interface Listener : BackPressedListener {
        fun onSearchFieldTextChanged(text: String)
        fun onBackIconClicked()
        fun onClearIconClicked()
    }

    fun setClearIconVisibility(isVisible: Boolean)
    fun clearSearchFieldText()
}