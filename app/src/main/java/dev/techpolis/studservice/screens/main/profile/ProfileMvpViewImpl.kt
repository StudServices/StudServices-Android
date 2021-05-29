package dev.techpolis.studservice.screens.main.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dev.techpolis.studservice.R
import dev.techpolis.studservice.screens.common.mvp.MvpViewObservableBase

class ProfileMvpViewImpl(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?
) : MvpViewObservableBase<ProfileMvpView.Listener>(), ProfileMvpView {
    override var rootView: View =
        layoutInflater.inflate(R.layout.fragment_main__profile, parent, false)

}