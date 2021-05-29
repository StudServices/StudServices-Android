package dev.techpolis.studservice.screens.main.userservices.requests

import dev.techpolis.studservice.screens.common.mvp.MvpPresenter

class UserServiceRequestsPresenter: MvpPresenter<UserServiceRequestsMvpView>, UserServiceRequestsMvpView.Listener {
    private lateinit var view: UserServiceRequestsMvpView

    override fun bindView(view: UserServiceRequestsMvpView) {
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

    override fun onServiceOfferClicked() {
//        TODO("Not yet implemented")
    }

    override fun onBackPressed(): Boolean {
//        TODO("Not yet implemented")
        return false
    }
}