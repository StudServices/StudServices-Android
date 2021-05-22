package dev.techpolis.studservice.views.main.userservices.offers

import dev.techpolis.studservice.common.mvp.MvpViewObservable
import dev.techpolis.studservice.common.nav.BackPressedListener

interface UserServiceOffersMvpView: MvpViewObservable<UserServiceOffersMvpView.Listener> {
    interface Listener : BackPressedListener {
        fun onServiceOfferClicked()
    }

}