package dev.techpolis.studservice.views.main.services

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dev.techpolis.studservice.R
import dev.techpolis.studservice.common.mvp.MvpViewObservableBase

class ServicesMvpViewImpl(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?
) : MvpViewObservableBase<ServicesMvpView.Listener>(), ServicesMvpView {
    override var rootView: View =
        layoutInflater.inflate(R.layout.fragment_main__services, parent, false)

}