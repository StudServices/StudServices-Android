package dev.techpolis.studservice.screens.main.services.offers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dev.techpolis.studservice.R
import dev.techpolis.studservice.screens.common.mvp.MvpViewObservableBase

class ServiceOffersMvpViewImpl(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?
) : MvpViewObservableBase<ServiceOffersMvpView.Listener>(), ServiceOffersMvpView {
    override var rootView: View =
        layoutInflater.inflate(R.layout.fragment_main__services__offers, parent, false)

}