package dev.techpolis.studservice.screens.main.userservices.newservice

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dev.techpolis.studservice.R
import dev.techpolis.studservice.screens.common.mvp.MvpViewObservableBase

class NewServiceMvpViewImpl(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?
) : MvpViewObservableBase<NewServiceMvpView.Listener>(), NewServiceMvpView {
    override var rootView: View =
        layoutInflater.inflate(R.layout.fragment_main__user_services__new_service, parent, false)

}