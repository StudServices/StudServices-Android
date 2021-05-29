package dev.techpolis.studservice.screens.main.userservices.requests

import dev.techpolis.studservice.data.entities.ServiceEntity
import dev.techpolis.studservice.screens.common.mvp.MvpViewObservable
import dev.techpolis.studservice.screens.common.nav.BackPressedListener

interface UserServiceRequestsMvpView: MvpViewObservable<UserServiceRequestsMvpView.Listener> {
    interface Listener : BackPressedListener {
        fun onServiceRequestClicked(service: ServiceEntity)
    }
    fun bindData(listRequests: List<ServiceEntity>)
}