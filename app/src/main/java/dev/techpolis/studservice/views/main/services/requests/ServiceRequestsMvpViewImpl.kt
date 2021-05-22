package dev.techpolis.studservice.views.main.services.requests

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dev.techpolis.studservice.R
import dev.techpolis.studservice.common.mvp.MvpViewObservableBase

class ServiceRequestsMvpViewImpl(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?
) : MvpViewObservableBase<ServiceRequestsMvpView.Listener>(), ServiceRequestsMvpView {
    override var rootView: View =
        layoutInflater.inflate(R.layout.fragment_main__services__requests, parent, false)

}