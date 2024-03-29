package dev.techpolis.studservice.screens.main.service_info

import android.util.Log
import dev.techpolis.studservice.providers.ServiceInfoProvider
import dev.techpolis.studservice.screens.common.mvp.MvpPresenter
import dev.techpolis.studservice.screens.common.nav.BackPressDispatcher
import dev.techpolis.studservice.screens.common.nav.main.MainScreenRouter

class ServiceInfoPresenter(
    private val serviceInfoProvider: ServiceInfoProvider,
    private val backPressDispatcher: BackPressDispatcher,
    private val mainScreenRouter: MainScreenRouter
) : MvpPresenter<ServiceInfoMvpView>, ServiceInfoMvpView.Listener {
    private lateinit var view: ServiceInfoMvpView

    override fun bindView(view: ServiceInfoMvpView) {
        this.view = view
    }

    override fun onStart() {
        view.registerListener(this)
        backPressDispatcher.registerListener(this)
        view.bindData(serviceInfoProvider.service)
    }

    override fun onStop() {
        view.unregisterListener(this)
        backPressDispatcher.unregisterListener(this)
    }

    override fun onDestroy() {
//        TODO("Not yet implemented")
    }

    override fun onContactBtnClicked() {
        mainScreenRouter.toProfile()
    }

    override fun onBackArrowBtnClicked() {
        onBackPressed()
    }

    override fun onBackPressed(): Boolean {
        Log.e("ServiceInfo", "NavigateUp")
        mainScreenRouter.navigateUp()
        return true
    }
}