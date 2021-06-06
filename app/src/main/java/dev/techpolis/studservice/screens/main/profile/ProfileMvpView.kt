package dev.techpolis.studservice.screens.main.profile

import dev.techpolis.studservice.screens.common.mvp.MvpViewObservable
import dev.techpolis.studservice.screens.common.nav.BackPressedListener

interface ProfileMvpView: MvpViewObservable<ProfileMvpView.Listener> {
    interface Listener : BackPressedListener {
        fun onBackBtnClicked()
    }

}