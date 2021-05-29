package dev.techpolis.studservice.screens.main.userservices.offers

import dev.techpolis.studservice.screens.common.mvp.MvpViewObservable
import dev.techpolis.studservice.screens.common.nav.BackPressedListener

interface UserServiceOffersMvpView: MvpViewObservable<UserServiceOffersMvpView.Listener> {
    interface Listener : BackPressedListener {
        fun onServiceOfferClicked()
    }

}