package dev.techpolis.studservice.screens.main.filters

import dev.techpolis.studservice.screens.common.mvp.MvpViewObservable
import dev.techpolis.studservice.screens.common.nav.BackPressedListener

interface FiltersMvpView: MvpViewObservable<FiltersMvpView.Listener> {
    interface Listener : BackPressedListener {
    }

}