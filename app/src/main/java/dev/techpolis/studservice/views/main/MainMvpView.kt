package dev.techpolis.studservice.views.main

import dev.techpolis.studservice.common.mvp.MvpViewObservable
import dev.techpolis.studservice.common.nav.BackPressedListener
import dev.techpolis.studservice.views.auth.signin.SignInMvpView

interface MainMvpView : MvpViewObservable<SignInMvpView.Listener> {
    interface Listener : BackPressedListener {
        fun onMyServicesClicked()
        fun onProfileServices()
        fun onServicesClicked()
    }

}