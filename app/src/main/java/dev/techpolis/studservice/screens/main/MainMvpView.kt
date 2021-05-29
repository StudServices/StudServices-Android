package dev.techpolis.studservice.screens.main

import android.view.MenuItem
import dev.techpolis.studservice.screens.common.mvp.MvpViewObservable
import dev.techpolis.studservice.screens.common.nav.BackPressedListener

interface MainMvpView: MvpViewObservable<MainMvpView.Listener> {
    interface Listener: BackPressedListener {
        fun onNavigationItemSelectedListener(item: MenuItem)
    }
}