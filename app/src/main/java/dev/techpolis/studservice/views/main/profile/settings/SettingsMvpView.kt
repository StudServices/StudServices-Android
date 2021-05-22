package dev.techpolis.studservice.views.main.profile.settings

import dev.techpolis.studservice.common.mvp.MvpViewObservable
import dev.techpolis.studservice.common.nav.BackPressedListener

interface SettingsMvpView: MvpViewObservable<SettingsMvpView.Listener> {
    interface Listener : BackPressedListener {
    }

}