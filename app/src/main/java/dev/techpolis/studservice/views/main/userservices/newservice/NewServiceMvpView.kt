package dev.techpolis.studservice.views.main.userservices.newservice

import dev.techpolis.studservice.common.mvp.MvpViewObservable
import dev.techpolis.studservice.common.nav.BackPressedListener

interface NewServiceMvpView: MvpViewObservable<NewServiceMvpView.Listener> {
    interface Listener : BackPressedListener {
    }

}