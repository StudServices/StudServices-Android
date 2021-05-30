package dev.techpolis.studservice.screens.main.userservices

import dev.techpolis.studservice.screens.common.mvp.MvpViewObservable
import dev.techpolis.studservice.screens.common.nav.BackPressedListener

interface UserServicesMvpView: MvpViewObservable<UserServicesMvpView.Listener> {
    interface Listener : BackPressedListener {
        fun onSearchViewFocus()
    }

}