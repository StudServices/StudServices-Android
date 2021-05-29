package dev.techpolis.studservice.screens.main.services.requests

import dev.techpolis.studservice.screens.common.mvp.MvpViewObservable
import dev.techpolis.studservice.screens.common.nav.BackPressedListener

interface ServiceRequestsMvpView: MvpViewObservable<ServiceRequestsMvpView.Listener> {
    interface Listener : BackPressedListener {
    }

}