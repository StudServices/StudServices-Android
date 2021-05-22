package dev.techpolis.studservice.views.main.userservices.requests

import dev.techpolis.studservice.common.mvp.MvpViewObservable
import dev.techpolis.studservice.common.nav.BackPressedListener

interface UserServiceRequestsMvpView: MvpViewObservable<UserServiceRequestsMvpView.Listener> {
    interface Listener : BackPressedListener {
        fun onServiceOfferClicked()
    }

}