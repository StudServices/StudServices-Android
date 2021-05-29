package dev.techpolis.studservice.screens.main.profile.settings

import dev.techpolis.studservice.screens.common.mvp.MvpViewObservable
import dev.techpolis.studservice.screens.common.nav.BackPressedListener

interface SettingsMvpView: MvpViewObservable<SettingsMvpView.Listener> {
    interface Listener : BackPressedListener {
    }

}