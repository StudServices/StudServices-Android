package dev.techpolis.studservice.screens.main.services.offers

import dev.techpolis.studservice.screens.common.mvp.MvpPresenter

class ServiceOffersPresenter: MvpPresenter<ServiceOffersMvpView>, ServiceOffersMvpView.Listener {
    private lateinit var view: ServiceOffersMvpView

    override fun bindView(view: ServiceOffersMvpView) {
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