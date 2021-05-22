package dev.techpolis.studservice.views.main.services.requests

import dev.techpolis.studservice.common.mvp.MvpViewObservable
import dev.techpolis.studservice.common.nav.BackPressedListener

interface ServiceRequestsMvpView: MvpViewObservable<ServiceRequestsMvpView.Listener> {
    interface Listener : BackPressedListener {
        fun onServiceRequestClicked()
    }

}