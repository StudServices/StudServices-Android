package dev.techpolis.studservice.screens.main.services

import android.util.Log
import dev.techpolis.studservice.screens.common.mvp.MvpPresenter
import dev.techpolis.studservice.screens.common.nav.BackPressDispatcher
import dev.techpolis.studservice.screens.common.nav.main.MainScreenRouter
import dev.techpolis.studservice.screens.common.nav.main.MainScreenRouterImpl

class ServicesPresenter(
    private val backPressDispatcher: BackPressDispatcher,
    private val mainScreenRouter: MainScreenRouter
): MvpPresenter<ServicesMvpView>, ServicesMvpView.Listener {
    private lateinit var view: ServicesMvpView

    override fun bindView(view: ServicesMvpView) {
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

    override fun onBackPressed(): Boolean {
        Log.e("ServicesPresenter", "NavigateUp")
        mainScreenRouter.navigateUp()
        return true
    }
}