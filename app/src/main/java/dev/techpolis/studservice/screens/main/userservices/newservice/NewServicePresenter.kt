package dev.techpolis.studservice.screens.main.userservices.newservice

import dev.techpolis.studservice.screens.common.mvp.MvpPresenter

class NewServicePresenter: MvpPresenter<NewServiceMvpView>, NewServiceMvpView.Listener {
    private lateinit var view: NewServiceMvpView

    override fun bindView(view: NewServiceMvpView) {
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