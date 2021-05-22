package dev.techpolis.studservice.views.main.profile.settings

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dev.techpolis.studservice.R
import dev.techpolis.studservice.common.mvp.MvpViewObservableBase

class SettingsMvpViewImpl(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?
) : MvpViewObservableBase<SettingsMvpView.Listener>(), SettingsMvpView {
    override var rootView: View =
        layoutInflater.inflate(R.layout.fragment_main__profile__settings, parent, false)

}