package dev.techpolis.studservice.screens.main.my_profile

import dev.techpolis.studservice.screens.common.mvp.MvpPresenter
import dev.techpolis.studservice.screens.common.nav.BackPressDispatcher
import dev.techpolis.studservice.screens.common.nav.app.AppScreenRouter
import dev.techpolis.studservice.screens.common.nav.main.MainScreenRouter

class MyProfilePresenter(
    private val appScreenRouter: AppScreenRouter,
    private val mainScreenRouter: MainScreenRouter,
    private val backPressDispatcher: BackPressDispatcher
): MvpPresenter<MyProfileMvpView>, MyProfileMvpView.Listener {
    private lateinit var view: MyProfileMvpView

    override fun bindView(view: MyProfileMvpView) {
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

    override fun onLogoutBtnClicked() {
        appScreenRouter.toAuth()
    }

    override fun onBackPressed(): Boolean {
        return false
    }
}