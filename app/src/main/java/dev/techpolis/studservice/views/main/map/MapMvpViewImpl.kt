package dev.techpolis.studservice.views.main.map

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dev.techpolis.studservice.R
import dev.techpolis.studservice.common.mvp.MvpViewObservableBase

class MapMvpViewImpl(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?
) : MvpViewObservableBase<MapMvpView.Listener>(), MapMvpView {
    override var rootView: View =
        layoutInflater.inflate(R.layout.fragment_main__map, parent, false)

}