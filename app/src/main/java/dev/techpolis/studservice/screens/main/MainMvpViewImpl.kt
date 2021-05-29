package dev.techpolis.studservice.screens.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ncapdevi.fragnav.FragNavTransactionOptions
import dev.techpolis.studservice.R
import dev.techpolis.studservice.screens.common.mvp.MvpViewObservableBase
import dev.techpolis.studservice.screens.common.nav.main.MainScreenRouter

class MainMvpViewImpl(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?
) : MvpViewObservableBase<MainMvpView.Listener>(), MainMvpView {

    override fun bottomBarSetSelected(index: Int) {
        bottomBar.selectedItemId = index
    }

    override fun isBottomBarVisible(isVisible: Boolean) {
        bottomBar.isVisible = isVisible
    }

    override var rootView: View = layoutInflater.inflate(R.layout.fragment_main, parent, false)

    private val bottomBar: BottomNavigationView = findViewById(R.id.fragment_main__bottom_nav_view)

    init {
        bottomBar.setOnNavigationItemSelectedListener { item ->
            listeners.forEach { it.onNavigationItemSelectedListener(item) }
            return@setOnNavigationItemSelectedListener true
        }
    }
}