package dev.techpolis.studservice.screens.main.services.requests

import dev.techpolis.studservice.data.entities.ServiceEntity
import dev.techpolis.studservice.screens.common.mvp.MvpViewObservable
import dev.techpolis.studservice.screens.common.nav.BackPressedListener

interface ServiceRequestsMvpView: MvpViewObservable<ServiceRequestsMvpView.Listener> {
    interface Listener : BackPressedListener {
        fun onServiceClicked(service: ServiceEntity)
    }
    fun bindData(listRequests: List<ServiceEntity>)
}