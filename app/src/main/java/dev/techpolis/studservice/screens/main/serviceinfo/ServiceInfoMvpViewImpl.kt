package dev.techpolis.studservice.screens.main.serviceinfo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dev.techpolis.studservice.R
import dev.techpolis.studservice.screens.common.mvp.MvpViewObservableBase

class ServiceInfoMvpViewImpl(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?
) : MvpViewObservableBase<ServiceInfoMvpView.Listener>(), ServiceInfoMvpView {
    override var rootView: View =
        layoutInflater.inflate(R.layout.fragment_main__service_info, parent, false)

}