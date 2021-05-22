package dev.techpolis.studservice.views.main.serviceinfo

import dev.techpolis.studservice.common.mvp.MvpViewObservable
import dev.techpolis.studservice.common.nav.BackPressedListener

interface ServiceInfoMvpView: MvpViewObservable<ServiceInfoMvpView.Listener> {
    interface Listener : BackPressedListener {
    }

}