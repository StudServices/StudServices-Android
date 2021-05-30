package dev.techpolis.studservice.screens.main.profile.settings

import dev.techpolis.studservice.screens.common.mvp.MvpPresenter
import dev.techpolis.studservice.screens.common.nav.BackPressDispatcher
import dev.techpolis.studservice.screens.common.nav.main.MainScreenRouter

class SettingsPresenter(
    private val mainScreenRouter: MainScreenRouter,
    private val backPressDispatcher: BackPressDispatcher,
) : MvpPresenter<SettingsMvpView>, SettingsMvpView.Listener {
    private lateinit var view: SettingsMvpView

    override fun bindView(view: SettingsMvpView) {
        this.view = view
    }

    override fun onStart() {
        view.registerListener(this)
        backPressDispatcher.registerListener(this)
    }

    override fun onStop() {
        view.unregisterListener(this)
        backPressDispatcher.unregisterListener(this)
    }

    override fun onDestroy() {
        TODO("Not yet implemented")
    }

    override fun onGeographyChanged(geography: String) {
        TODO("Not yet implemented")
    }


    override fun onDescriptionFieldTextChanged(text: String) {
        TODO("Not yet implemented")
    }

    override fun onBackPressed(): Boolean {
//        TODO("Not yet implemented")
        return false
    }
}