package dev.techpolis.studservice.views.main.profile

import dev.techpolis.studservice.common.mvp.MvpViewObservable
import dev.techpolis.studservice.common.nav.BackPressedListener

interface ProfileMvpView: MvpViewObservable<ProfileMvpView.Listener> {
    interface Listener : BackPressedListener {
    }

}