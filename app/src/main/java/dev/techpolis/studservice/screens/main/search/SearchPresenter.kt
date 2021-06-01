package dev.techpolis.studservice.screens.main.search

import android.util.Log
import dev.techpolis.studservice.screens.common.mvp.MvpPresenter
import dev.techpolis.studservice.screens.common.nav.BackPressDispatcher
import dev.techpolis.studservice.screens.common.nav.main.MainScreenRouter
import dev.techpolis.studservice.data.model.ServiceTypeEnum
import dev.techpolis.studservice.screens.main.search.filters.FiltersMvpView

class SearchPresenter(
    private val mainScreenRouter: MainScreenRouter,
    private val backPressDispatcher: BackPressDispatcher,
): MvpPresenter<SearchMvpView>, SearchMvpView.Listener, FiltersMvpView.Listener {

    private lateinit var view: SearchMvpView
    private lateinit var filtersMvpView: FiltersMvpView

    override fun bindView(view: SearchMvpView) {
        this.view = view
        this.filtersMvpView = view.getFiltersMvpView()
    }

    override fun onStart() {
        view.registerListener(this)
        filtersMvpView.registerListener(this)
        backPressDispatcher.registerListener(this)
    }

    override fun onStop() {
        view.unregisterListener(this)
        filtersMvpView.unregisterListener(this)
        backPressDispatcher.unregisterListener(this)
    }

    override fun onDestroy() {
//        TODO("Not yet implemented")
    }

    override fun onSearchFieldTextChanged(text: String) {
        if (text.isNotEmpty()) {
            //TODO(Search)
            view.setClearIconVisibility(true)
        } else {
            view.setClearIconVisibility(false)
        }
    }

    override fun onBackIconClicked() {
        mainScreenRouter.navigateUp()
    }

    override fun onClearIconClicked() {
        view.clearSearchFieldText()
    }

    override fun onFiltersChanged(
        serviceType: ServiceTypeEnum,
        location: String,
        tags: List<String>
    ) {
        //TODO("Not yet implemented")
    }

    override fun onArrowClicked(isContentVisible: Boolean) {
        if (isContentVisible) {
            filtersMvpView.setContentVisible(false)
        } else {
            filtersMvpView.setContentVisible(true)
        }
    }

    override fun onChipDeleted(tagText: String) {
        //TODO("Not yet implemented")
    }

    override fun onChipAdded(tagText: String) {
        //TODO("Not yet implemented")
    }

    override fun onBackPressed(): Boolean {
        Log.e("SearchPresenter", "NavigateUp")
        mainScreenRouter.navigateUp()
        return true
    }
}
