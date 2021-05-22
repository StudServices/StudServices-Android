package dev.techpolis.studservice.views.main.filters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dev.techpolis.studservice.R
import dev.techpolis.studservice.common.mvp.MvpViewObservableBase

class FiltersMvpViewImpl(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?
) : MvpViewObservableBase<FiltersMvpView.Listener>(), FiltersMvpView {
    override var rootView: View =
        layoutInflater.inflate(R.layout.fragment_main__filters, parent, false)

}