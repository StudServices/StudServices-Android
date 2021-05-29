package dev.techpolis.studservice.screens.main.filters

import dev.techpolis.studservice.screens.common.mvp.MvpViewObservable
import dev.techpolis.studservice.screens.common.nav.BackPressedListener
import dev.techpolis.studservice.data.model.ServiceTypeEnum

interface FiltersMvpView: MvpViewObservable<FiltersMvpView.Listener> {
    interface Listener : BackPressedListener {
        fun onFilterBtnClicked(serviceType: ServiceTypeEnum,
                               geography: String,
                               tags: List<String>)
    }

}