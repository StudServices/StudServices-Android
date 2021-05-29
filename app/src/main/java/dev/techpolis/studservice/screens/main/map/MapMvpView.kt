package dev.techpolis.studservice.screens.main.map

import dev.techpolis.studservice.screens.common.mvp.MvpViewObservable
import dev.techpolis.studservice.screens.common.nav.BackPressedListener

interface MapMvpView: MvpViewObservable<MapMvpView.Listener> {
    interface Listener : BackPressedListener {
    }

}