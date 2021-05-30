package dev.techpolis.studservice.screens.main.userservices

import android.util.Log
import dev.techpolis.studservice.screens.common.mvp.MvpPresenter
import dev.techpolis.studservice.screens.common.nav.BackPressDispatcher
import dev.techpolis.studservice.screens.common.nav.main.MainScreenRouter

class UserServicesPresenter(
    private val backPressDispatcher: BackPressDispatcher,
    private val mainScreenRouter: MainScreenRouter
): MvpPresenter<UserServicesMvpView>, UserServicesMvpView.Listener {
    private lateinit var view: UserServicesMvpView

    override fun bindView(view: UserServicesMvpView) {
        this.view = view
    }

    override fun onStart() {
        view.registerListener(this)
        backPressDispatcher.registerListener(this)
        Log.e("UserServicesPresenter", "onStart")
    }

    override fun onStop() {
        view.unregisterListener(this)
        backPressDispatcher.unregisterListener(this)
        Log.e("UserServicesPresenter", "onStop")
    }

    override fun onDestroy() {
//        TODO("Not yet implemented")
    }

    override fun onSearchViewFocus() {
        mainScreenRouter.toSearchScreen()
    }

    override fun onBackPressed(): Boolean {
        Log.e("UserServicesPresenter", "onBackPressed")
        mainScreenRouter.navigateUp()
        return true
    }
}