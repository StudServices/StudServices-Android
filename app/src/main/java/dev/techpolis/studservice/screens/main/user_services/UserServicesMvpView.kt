package dev.techpolis.studservice.screens.main.user_services

import dev.techpolis.studservice.screens.common.mvp.MvpViewObservable
import dev.techpolis.studservice.screens.common.nav.BackPressedListener

interface UserServicesMvpView: MvpViewObservable<UserServicesMvpView.Listener> {
    interface Listener : BackPressedListener {
        fun onSearchViewFocus()
        fun onNewBtnClicked()
    }

}