package dev.techpolis.studservice.screens.main.userservices

import dev.techpolis.studservice.screens.common.mvp.MvpPresenter

class UserServicesPresenter: MvpPresenter<UserServicesMvpView>, UserServicesMvpView.Listener {
    private lateinit var view: UserServicesMvpView

    override fun bindView(view: UserServicesMvpView) {
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