package dev.techpolis.studservice.screens.main.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageButton
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.RecyclerView
import dev.techpolis.studservice.R
import dev.techpolis.studservice.screens.common.mvp.MvpViewObservableBase
import dev.techpolis.studservice.screens.main.search.filters.FiltersMvpView
import dev.techpolis.studservice.screens.main.search.filters.FiltersMvpViewImpl


class SearchMvpViewImpl(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?
) : MvpViewObservableBase<SearchMvpView.Listener>(), SearchMvpView {

    override var rootView: View =
        layoutInflater.inflate(R.layout.fragment_main__search, parent, false)

    private val etSearch: AppCompatEditText =
        findViewById(R.id.fragment_main__search__edit_text)
    private val rvResult: RecyclerView = findViewById(R.id.fragment_main__search__recycler)
    private val ibBackArrow: AppCompatImageButton =
        findViewById(R.id.fragment_main__search__ic_back)
    private val ibClear: AppCompatImageButton = findViewById(R.id.fragment_main__search__ic_clear)

    private val filtersMvpView: FiltersMvpView = FiltersMvpViewImpl(
        findViewById(R.id.fragment_main__search__filters),
        layoutInflater
    )


    init {
        etSearch.requestFocus()
        ibBackArrow.setOnClickListener { listeners.forEach { it.onBackIconClicked() } }
        ibClear.setOnClickListener { listeners.forEach { it.onClearIconClicked() } }
        etSearch.doOnTextChanged { text, _, _, _ ->
            listeners.forEach { it.onSearchFieldTextChanged(text.toString()) }
        }
    }

    override fun setClearIconVisibility(isVisible: Boolean) {
        ibClear.isVisible = isVisible
    }

    override fun clearSearchFieldText() {
        etSearch.text?.clear()
    }

    override fun getFiltersMvpView(): FiltersMvpView = filtersMvpView

}