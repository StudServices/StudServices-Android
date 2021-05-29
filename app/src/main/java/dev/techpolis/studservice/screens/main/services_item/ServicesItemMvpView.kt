package dev.techpolis.studservice.screens.main.services_item

import dev.techpolis.studservice.data.entities.ServiceEntity
import dev.techpolis.studservice.screens.common.mvp.MvpView

interface ServicesItemMvpView : MvpView {

    interface Listener {
        fun onServiceClicked(service: ServiceEntity)
    }

    fun bindData(service: ServiceEntity)
}