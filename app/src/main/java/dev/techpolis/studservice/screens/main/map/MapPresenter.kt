package dev.techpolis.studservice.screens.main.map

import dev.techpolis.studservice.screens.common.mvp.MvpPresenter

class MapPresenter: MvpPresenter<MapMvpView>, MapMvpView.Listener {
    private lateinit var view: MapMvpView

    override fun bindView(view: MapMvpView) {
        this.view = view
    }

    override fun onStart() {
//        TODO("Not yet implemented")
    }

    override fun onStop() {
//        TODO("Not yet implemented")
    }

    override fun onDestroy() {
//        TODO("Not yet implemented")
    }

    override fun onBackPressed(): Boolean {
//        TODO("Not yet implemented")
        return false
    }
}