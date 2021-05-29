package dev.techpolis.studservice.screens.main.profile.settings

import android.accounts.AuthenticatorDescription
import dev.techpolis.studservice.screens.common.mvp.MvpPresenter
import dev.techpolis.studservice.screens.common.nav.BackPressDispatcher
import dev.techpolis.studservice.screens.common.nav.app.AppScreenRouter

class SettingsPresenter(
    private val appScreenRouter: AppScreenRouter,
    private val backPressDispatcher: BackPressDispatcher,
) : MvpPresenter<SettingsMvpView>, SettingsMvpView.Listener {
    private lateinit var view: SettingsMvpView
    private lateinit var geography: String
    private lateinit var description: String
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
//        TODO("Not yet implemented")
    }

    override fun onGeographyChanged(geography: String) {
        this.geography = geography
    }

    override fun onChangePhotoBtnClicked() {
        TODO("Not yet implemented")
    }

    override fun onDescriptionFieldTextChanged(text: String) {
        this.description = text
    }

    override fun onBackPressed(): Boolean {
//        TODO("Not yet implemented")
        return false
    }
}