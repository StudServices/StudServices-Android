package dev.techpolis.studservice.screens.common.mvp

import dev.techpolis.studservice.screens.main.my_profile.MyProfilePresenter

interface MvpViewObservable<ListenerType> : MvpView {
    fun registerListener(listener: ListenerType)
    fun unregisterListener(listener: ListenerType)
}