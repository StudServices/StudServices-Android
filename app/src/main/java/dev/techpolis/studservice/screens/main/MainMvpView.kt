package dev.techpolis.studservice.screens.main

import android.view.MenuItem
import dev.techpolis.studservice.screens.common.mvp.MvpViewObservable
import dev.techpolis.studservice.screens.common.nav.BackPressedListener
import dev.techpolis.studservice.screens.common.nav.main.MainScreenRouter

interface MainMvpView: MvpViewObservable<MainMvpView.Listener> {
    interface Listener: BackPressedListener {
        fun onNavigationItemSelectedListener(item: MenuItem)
    }
    fun bottomBarSetSelected(index: Int)
    fun isBottomBarVisible(isVisible: Boolean)
}