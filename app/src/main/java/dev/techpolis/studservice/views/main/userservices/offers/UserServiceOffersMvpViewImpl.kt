package dev.techpolis.studservice.views.main.userservices.offers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dev.techpolis.studservice.R
import dev.techpolis.studservice.common.mvp.MvpViewObservableBase

class UserServiceOffersMvpViewImpl(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?
) : MvpViewObservableBase<UserServiceOffersMvpView.Listener>(), UserServiceOffersMvpView {
    override var rootView: View =
        layoutInflater.inflate(R.layout.fragment_main__user_services__offers, parent, false)

}