package dev.techpolis.studservice.screens.main.services

import dev.techpolis.studservice.screens.common.mvp.MvpViewObservable
import dev.techpolis.studservice.screens.common.nav.BackPressedListener

interface ServicesMvpView: MvpViewObservable<ServicesMvpView.Listener> {
    interface Listener : BackPressedListener {
    }

}