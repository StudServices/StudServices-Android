package dev.techpolis.studservice.screens.main.my_profile

import dev.techpolis.studservice.screens.common.mvp.MvpViewObservable
import dev.techpolis.studservice.screens.common.nav.BackPressedListener
import dev.techpolis.studservice.screens.main.profile.ProfileMvpView

interface MyProfileMvpView: MvpViewObservable<MyProfileMvpView.Listener> {
    interface Listener : BackPressedListener {
        fun onLogoutBtnClicked()
    }

}