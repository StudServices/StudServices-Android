package dev.techpolis.studservice.screens.main.services.offers

import dev.techpolis.studservice.data.entities.ServiceEntity
import dev.techpolis.studservice.screens.common.mvp.MvpViewObservable
import dev.techpolis.studservice.screens.common.nav.BackPressedListener

interface ServiceOffersMvpView: MvpViewObservable<ServiceOffersMvpView.Listener> {
    interface Listener : BackPressedListener {
        fun onServiceOfferClicked(service: ServiceEntity)
    }

    fun bindData(listOffers: List<ServiceEntity>)

}