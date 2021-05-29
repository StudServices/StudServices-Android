package dev.techpolis.studservice.screens.main.userservices.requests

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dev.techpolis.studservice.R
import dev.techpolis.studservice.screens.common.mvp.MvpViewObservableBase

class UserServiceRequestsMvpViewImpl(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?
) : MvpViewObservableBase<UserServiceRequestsMvpView.Listener>(), UserServiceRequestsMvpView {
    override var rootView: View =
        layoutInflater.inflate(R.layout.fragment_main__user_services__requests, parent, false)

}