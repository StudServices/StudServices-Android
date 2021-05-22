package dev.techpolis.studservice.views.main.userservices

import dev.techpolis.studservice.common.mvp.MvpViewObservable
import dev.techpolis.studservice.common.nav.BackPressedListener

interface UserServicesMvpView: MvpViewObservable<UserServicesMvpView.Listener> {
    interface Listener : BackPressedListener {
    }

}