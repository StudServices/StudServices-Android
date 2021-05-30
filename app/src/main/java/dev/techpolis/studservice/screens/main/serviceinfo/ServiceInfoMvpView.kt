package dev.techpolis.studservice.screens.main.serviceinfo

import dev.techpolis.studservice.data.entities.ServiceEntity
import dev.techpolis.studservice.screens.common.mvp.MvpViewObservable
import dev.techpolis.studservice.screens.common.nav.BackPressedListener

interface ServiceInfoMvpView: MvpViewObservable<ServiceInfoMvpView.Listener> {
    interface Listener : BackPressedListener {
        fun onContactBtnClicked()
        fun onBackArrowBtnClicked()
    }
    fun bindData(service: ServiceEntity)
}