package dev.techpolis.studservice.screens.main.profile

import dev.techpolis.studservice.screens.common.mvp.MvpPresenter
import dev.techpolis.studservice.screens.common.nav.BackPressDispatcher
import dev.techpolis.studservice.screens.common.nav.main.MainScreenRouter

class ProfilePresenter(
    private val mainScreenRouter: MainScreenRouter,
    private val backPressDispatcher: BackPressDispatcher
): MvpPresenter<ProfileMvpView>, ProfileMvpView.Listener {
    private lateinit var view: ProfileMvpView

    override fun bindView(view: ProfileMvpView) {
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
//        TODO("Not yet implemented")
        return false
    }
}