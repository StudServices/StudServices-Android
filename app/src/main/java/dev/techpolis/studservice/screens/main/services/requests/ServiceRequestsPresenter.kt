package dev.techpolis.studservice.screens.main.services.requests

import dev.techpolis.studservice.screens.common.mvp.MvpPresenter

class ServiceRequestsPresenter: MvpPresenter<ServiceRequestsMvpView>, ServiceRequestsMvpView.Listener {
    private lateinit var view: ServiceRequestsMvpView

    override fun bindView(view: ServiceRequestsMvpView) {
        this.view = view
    }

    override fun onStart() {
//        TODO("Not yet implemented")
    }

    override fun onStop() {
//        TODO("Not yet implemented")
    }

    override fun onDestroy() {
//        TODO("Not yet implemented")
    }

    override fun onBackPressed(): Boolean {
//        TODO("Not yet implemented")
        return false
    }
}