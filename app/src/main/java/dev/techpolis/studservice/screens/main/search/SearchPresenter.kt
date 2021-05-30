package dev.techpolis.studservice.screens.main.search

import android.util.Log
import dev.techpolis.studservice.screens.common.mvp.MvpPresenter
import dev.techpolis.studservice.screens.common.nav.BackPressDispatcher
import dev.techpolis.studservice.screens.common.nav.main.MainScreenRouter
import dev.techpolis.studservice.data.model.ServiceTypeEnum

class SearchPresenter(
    private val mainScreenRouter: MainScreenRouter,
    private val backPressDispatcher: BackPressDispatcher,
): MvpPresenter<SearchMvpView>, SearchMvpView.Listener {
    private lateinit var view: SearchMvpView

    override fun bindView(view: SearchMvpView) {
        this.view = view
    }

    override fun onStart() {
        view.registerListener(this)
        backPressDispatcher.registerListener(this)
    }

    override fun onStop() {
        view.unregisterListener(this)
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

    override fun onBackPressed(): Boolean {
        Log.e("SearchPresenter", "NavigateUp")
        mainScreenRouter.navigateUp()
        return true
    }
}
