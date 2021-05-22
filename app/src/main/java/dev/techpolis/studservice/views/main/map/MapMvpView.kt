package dev.techpolis.studservice.views.main.map

import dev.techpolis.studservice.common.mvp.MvpViewObservable
import dev.techpolis.studservice.common.nav.BackPressedListener

interface MapMvpView: MvpViewObservable<MapMvpView.Listener> {
    interface Listener : BackPressedListener {
    }

}