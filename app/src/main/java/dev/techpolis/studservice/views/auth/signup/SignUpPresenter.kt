package dev.techpolis.studservice.views.auth.signup

import dev.techpolis.studservice.common.mvp.MvpPresenter
import dev.techpolis.studservice.common.nav.BackPressDispatcher
import dev.techpolis.studservice.common.nav.app.AppScreenRouterImpl

class SignUpPresenter(
    private val appScreenRouterImpl: AppScreenRouterImpl,
    private val backPressDispatcher: BackPressDispatcher,
) : MvpPresenter<SignUpMvpView>, SignUpMvpView.Listener {

    private lateinit var view: SignUpMvpView

    override fun bindView(view: SignUpMvpView) {
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

    override fun onSignUpBtnClicked(username: String, email: String, password: String) {
        TODO("Not yet implemented")
    }

    override fun onHaveAccTvClicked() {
        TODO("Not yet implemented")
    }

    override fun onUsernameFieldTextChanged(text: String) {
        TODO("Not yet implemented")
    }

    override fun onEmailFieldTextChanged(text: String) {
        TODO("Not yet implemented")
    }

    override fun onPasswordFieldTextChanged(text: String) {
        TODO("Not yet implemented")
    }

    override fun onBackPressed(): Boolean {
        TODO("Not yet implemented")
    }

    override fun onPasswordCorrectionFieldTextChanged(text: String) {
        TODO("Not yet implemented")
    }
}