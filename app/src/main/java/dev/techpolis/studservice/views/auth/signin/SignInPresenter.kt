package dev.techpolis.studservice.views.auth.signin

import dev.techpolis.studservice.common.mvp.MvpPresenter
import dev.techpolis.studservice.common.nav.BackPressDispatcher
import dev.techpolis.studservice.common.nav.app.AppScreenNavigator

class SignInPresenter(
    private val appScreenNavigator: AppScreenNavigator,
    private val backPressDispatcher: BackPressDispatcher,
) : MvpPresenter<SignInMvpView>, SignInMvpView.Listener{

    private lateinit var view: SignInMvpView

    override fun bindView(view: SignInMvpView) {
        TODO("Not yet implemented")
    }

    override fun onStart() {
        TODO("Not yet implemented")
    }

    override fun onStop() {
        TODO("Not yet implemented")
    }

    override fun onDestroy() {
        TODO("Not yet implemented")
    }

    override fun onSignInBtnClicked(username: String, password: String) {
        TODO("Not yet implemented")
    }

    override fun onForgotPasswordTvClicked() {
        TODO("Not yet implemented")
    }

    override fun onHaveNotAccTvClicked() {
        TODO("Not yet implemented")
    }

    override fun onUsernameFieldTextChanged(text: String) {
        TODO("Not yet implemented")
    }

    override fun onPasswordFieldTextChanged(text: String) {
        TODO("Not yet implemented")
    }

    override fun onBackPressed(): Boolean {
        TODO("Not yet implemented")
    }
}