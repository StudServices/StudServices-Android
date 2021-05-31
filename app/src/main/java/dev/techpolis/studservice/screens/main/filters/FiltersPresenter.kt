package dev.techpolis.studservice.screens.main.filters

import android.util.Log
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
        Log.e("FiltersPresenter", "onStart")
    }

    override fun onStop() {
        view.unregisterListener(this)
        backPressDispatcher.unregisterListener(this)
        Log.e("FiltersPresenter", "onStop")
    }

    override fun onDestroy() {
//        TODO("Not yet implemented")
    }

    override fun onFiltersChanged(
        serviceType: ServiceTypeEnum,
        location: String,
        tags: List<String>
    ) {
//        TODO("Not yet implemented")
    }

    override fun onBackPressed(): Boolean {
        Log.e("FiltersPresenter", "onBackPressed")
        mainScreenRouter.navigateUp()
        return true
    }
}
