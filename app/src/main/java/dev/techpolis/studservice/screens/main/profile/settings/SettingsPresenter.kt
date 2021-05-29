package dev.techpolis.studservice.screens.main.profile.settings

import dev.techpolis.studservice.screens.common.mvp.MvpPresenter

class SettingsPresenter: MvpPresenter<SettingsMvpView>, SettingsMvpView.Listener {
    private lateinit var view: SettingsMvpView

    override fun bindView(view: SettingsMvpView) {
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