package dev.techpolis.studservice.screens.main.serviceinfo

import dev.techpolis.studservice.screens.common.mvp.MvpPresenter

class ServiceInfoPresenter: MvpPresenter<ServiceInfoMvpView>, ServiceInfoMvpView.Listener {
    private lateinit var view: ServiceInfoMvpView

    override fun bindView(view: ServiceInfoMvpView) {
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