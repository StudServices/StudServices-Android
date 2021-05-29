package dev.techpolis.studservice.screens.main.userservices.newservice

import dev.techpolis.studservice.screens.common.mvp.MvpViewObservable
import dev.techpolis.studservice.screens.common.nav.BackPressedListener

interface NewServiceMvpView: MvpViewObservable<NewServiceMvpView.Listener> {
    interface Listener : BackPressedListener {
    }

}