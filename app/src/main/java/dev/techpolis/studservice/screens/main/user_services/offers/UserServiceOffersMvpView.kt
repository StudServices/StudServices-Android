package dev.techpolis.studservice.screens.main.user_services.offers

import dev.techpolis.studservice.data.entities.ServiceEntity
import dev.techpolis.studservice.screens.common.mvp.MvpViewObservable
import dev.techpolis.studservice.screens.common.nav.BackPressedListener

interface UserServiceOffersMvpView: MvpViewObservable<UserServiceOffersMvpView.Listener> {
    interface Listener : BackPressedListener {
        fun onServiceOfferClicked(service: ServiceEntity)
    }
    fun bindData(listOffers: List<ServiceEntity>)

}