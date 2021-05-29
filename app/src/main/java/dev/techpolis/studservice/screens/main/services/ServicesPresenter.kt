package dev.techpolis.studservice.screens.main.services

import dev.techpolis.studservice.screens.common.mvp.MvpPresenter

class ServicesPresenter: MvpPresenter<ServicesMvpView>, ServicesMvpView.Listener {
    private lateinit var view: ServicesMvpView

    override fun bindView(view: ServicesMvpView) {
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
        return false
    }
}