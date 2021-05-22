package dev.techpolis.studservice.views.main.filters

import dev.techpolis.studservice.common.mvp.MvpViewObservable
import dev.techpolis.studservice.common.nav.BackPressedListener

interface FiltersMvpView: MvpViewObservable<FiltersMvpView.Listener> {
    interface Listener : BackPressedListener {
    }

}