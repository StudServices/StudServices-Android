package dev.techpolis.studservice.screens.main.filters

import dev.techpolis.studservice.screens.common.mvp.MvpPresenter

class FiltersPresenter: MvpPresenter<FiltersMvpView>, FiltersMvpView.Listener {

    private lateinit var view: FiltersMvpView

    override fun bindView(view: FiltersMvpView) {
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
        return false
    }
}