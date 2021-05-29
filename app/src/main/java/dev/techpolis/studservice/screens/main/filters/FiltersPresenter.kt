package dev.techpolis.studservice.screens.main.filters

import dev.techpolis.studservice.screens.common.mvp.MvpPresenter
import dev.techpolis.studservice.screens.common.nav.BackPressDispatcher
import dev.techpolis.studservice.screens.common.nav.main.MainScreenRouter
import dev.techpolis.studservice.data.model.ServiceTypeEnum

class FiltersPresenter(
    private val mainScreenRouter: MainScreenRouter,
    private val backPressDispatcher: BackPressDispatcher,
): MvpPresenter<FiltersMvpView>, FiltersMvpView.Listener {
    private lateinit var view: FiltersMvpView

    override fun bindView(view: FiltersMvpView) {
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

    override fun onFilterBtnClicked(
        serviceType: ServiceTypeEnum,
        geography: String,
        tags: List<String>
    ) {
        TODO("Not yet implemented")
    }

    override fun onBackPressed(): Boolean {
        return false
    }
}
