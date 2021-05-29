package dev.techpolis.studservice.screens.main.userservices.offers

import dev.techpolis.studservice.screens.common.mvp.MvpPresenter

class UserServiceOffersPresenter: MvpPresenter<UserServiceOffersMvpView>, UserServiceOffersMvpView.Listener {
    private lateinit var view: UserServiceOffersMvpView

    override fun bindView(view: UserServiceOffersMvpView) {
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