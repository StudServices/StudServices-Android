package dev.techpolis.studservice.views.main.services.offers

import dev.techpolis.studservice.common.mvp.MvpViewObservable
import dev.techpolis.studservice.common.nav.BackPressedListener
import dev.techpolis.studservice.views.auth.signin.SignInMvpView

interface ServiceOffersMvpView: MvpViewObservable<ServiceOffersMvpView.Listener> {
    interface Listener : BackPressedListener {
        fun onServiceOfferClicked()
    }

}