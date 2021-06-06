package dev.techpolis.studservice.screens.main.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.techpolis.studservice.R
import dev.techpolis.studservice.data.entities.ServiceEntity
import dev.techpolis.studservice.data.model.ServiceTypeEnum
import dev.techpolis.studservice.screens.common.mvp.MvpViewObservableBase
import dev.techpolis.studservice.screens.common.mvp.factory.MvpViewFactory
import dev.techpolis.studservice.screens.main.search.filters.FiltersMvpView
import dev.techpolis.studservice.screens.main.search.filters.FiltersMvpViewImpl
import dev.techpolis.studservice.screens.main.services.ServicesAdapter
import dev.techpolis.studservice.screens.main.services_item.ServicesItemMvpView


class SearchMvpViewImpl(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?,
    viewFactory: MvpViewFactory
) : MvpViewObservableBase<SearchMvpView.Listener>(), SearchMvpView,
    ServicesItemMvpView.Listener{

    override var rootView: View =
        layoutInflater.inflate(R.layout.fragment_main__search, parent, false)

    private val etSearch: AppCompatEditText =
        findViewById(R.id.fragment_main__search__edit_text)
    private val rvResult: RecyclerView = findViewById(R.id.fragment_main__search__recycler)
    private val searchResultAdapter: SearchResultAdapter =
        viewFactory.createSearchResultAdapter(this, Glide.with(context))
    private val ibBackArrow: AppCompatImageButton =
        findViewById(R.id.fragment_main__search__ic_back)
    private val ibClear: AppCompatImageButton = findViewById(R.id.fragment_main__search__ic_clear)
    private val tvNoResults: AppCompatTextView = findViewById(R.id.fragment_main__search__not_found_tv)

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
        rvResult.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = searchResultAdapter
        }

    }

    override fun setClearIconVisibility(isVisible: Boolean) {
        ibClear.isVisible = isVisible
    }

    override fun clearSearchFieldText() {
        etSearch.text?.clear()
    }

    override fun getFiltersMvpView(): FiltersMvpView = filtersMvpView
    override fun onServiceClicked(service: ServiceEntity) {
        listeners.forEach {
            it.onServiceClicked(service)
        }
    }

    override fun bindData(listOffers: List<ServiceEntity>) {
        tvNoResults.isVisible = listOffers.isEmpty()
        searchResultAdapter.bindData(listOffers)
    }
}