package dev.techpolis.studservice.views.main.services

import dev.techpolis.studservice.common.mvp.MvpViewObservable
import dev.techpolis.studservice.common.nav.BackPressedListener

interface ServicesMvpView: MvpViewObservable<ServicesMvpView.Listener> {
    interface Listener : BackPressedListener {
    }

}