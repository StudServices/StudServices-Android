package dev.techpolis.studservice.screens.main

import android.util.Log
import android.view.MenuItem
import dev.techpolis.studservice.R
import dev.techpolis.studservice.screens.common.mvp.MvpPresenter
import dev.techpolis.studservice.screens.common.nav.BackPressDispatcher
import dev.techpolis.studservice.screens.common.nav.main.MainScreenRouter

class MainPresenter(
    private val mainScreenRouter: MainScreenRouter,
    private val backPressDispatcher: BackPressDispatcher,
) : MvpPresenter<MainMvpView>, MainMvpView.Listener {
    private lateinit var view: MainMvpView

    override fun bindView(view: MainMvpView) {
        this.view = view
    }

    override fun onStart() {
        view.registerListener(this)
        backPressDispatcher.registerListener(this)
        Log.e("MainPresenter", "onStart")
    }

    override fun onStop() {
        view.unregisterListener(this)
        backPressDispatcher.unregisterListener(this)
        Log.e("MainPresenter", "onStop")
    }

    override fun onDestroy() {
//        TODO("Not yet implemented")
    }

    override fun onNavigationItemSelectedListener(item: MenuItem) {
        when (item.itemId) {
            R.id.menu_item__userServices -> mainScreenRouter.toUserServices()
            R.id.menu_item__services -> mainScreenRouter.toServices()
            R.id.menu_item__my_profile -> mainScreenRouter.toMyProfile()
        }
    }

    override fun onBackPressed(): Boolean {
        return false
    }
}