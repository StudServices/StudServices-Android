package dev.techpolis.studservice.views.main.userservices

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dev.techpolis.studservice.R
import dev.techpolis.studservice.common.mvp.MvpViewObservableBase

class UserServicesMvpViewImpl(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?
) : MvpViewObservableBase<UserServicesMvpView.Listener>(), UserServicesMvpView {
    override var rootView: View =
        layoutInflater.inflate(R.layout.fragment_main__user_services, parent, false)

}