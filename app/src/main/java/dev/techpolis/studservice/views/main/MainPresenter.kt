package dev.techpolis.studservice.views.main

import dev.techpolis.studservice.common.mvp.MvpPresenter
import dev.techpolis.studservice.common.nav.BackPressDispatcher
import dev.techpolis.studservice.common.nav.app.AppScreenRouterImpl
import dev.techpolis.studservice.views.auth.signin.SignInMvpView

class MainPresenter(
    private val appScreenRouterImpl: AppScreenRouterImpl,
    private val backPressDispatcher: BackPressDispatcher,
): MvpPresenter<MainMvpView>, MainMvpView.Listener{

    private lateinit var view: MainMvpView
    override fun bindView(view: MainMvpView) {
        TODO("Not yet implemented")
    }

    override fun onStart() {
        TODO("Not yet implemented")
    }

    override fun onStop() {
        TODO("Not yet implemented")
    }

    override fun onDestroy() {
        TODO("Not yet implemented")
    }

    override fun onMyServicesClicked(username: String, password: String) {
        TODO("Not yet implemented")
    }

    override fun onProfileServices() {
        TODO("Not yet implemented")
    }

    override fun onServicesClicked() {
        TODO("Not yet implemented")
    }

    override fun onBackPressed(): Boolean {
        TODO("Not yet implemented")
    }
}