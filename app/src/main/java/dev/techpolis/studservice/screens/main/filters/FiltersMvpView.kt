package dev.techpolis.studservice.screens.main.filters

import dev.techpolis.studservice.screens.common.mvp.MvpViewObservable
import dev.techpolis.studservice.screens.common.nav.BackPressedListener
import dev.techpolis.studservice.data.model.ServiceTypeEnum

interface FiltersMvpView: MvpViewObservable<FiltersMvpView.Listener> {
    interface Listener : BackPressedListener {


        fun onFiltersChanged(serviceType: ServiceTypeEnum,
                             location: String,
                             tags: List<String>)
    }

}