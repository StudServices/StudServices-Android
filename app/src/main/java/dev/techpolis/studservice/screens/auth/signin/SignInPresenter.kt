package dev.techpolis.studservice.screens.auth.signin

import dev.techpolis.studservice.screens.common.mvp.MvpPresenter
import dev.techpolis.studservice.screens.common.nav.BackPressDispatcher
import dev.techpolis.studservice.screens.common.nav.app.AppScreenRouter

class SignInPresenter(
    private val appScreenRouter: AppScreenRouter,
    private val backPressDispatcher: BackPressDispatcher,
) : MvpPresenter<SignInMvpView>, SignInMvpView.Listener{

    private lateinit var view: SignInMvpView

    override fun bindView(view: SignInMvpView) {
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

    override fun onSignInBtnClicked(username: String, password: String) {
        appScreenRouter.toMain()
    }

    override fun onForgotPasswordTvClicked() {
//        TODO("Not yet implemented")
    }

    override fun onHaveNotAccTvClicked() {
        appScreenRouter.toSignUp()
    }

    override fun onUsernameFieldTextChanged(text: String) {
//        TODO("Not yet implemented")
    }

    override fun onPasswordFieldTextChanged(text: String) {
//        TODO("Not yet implemented")
    }

    override fun onBackPressed(): Boolean {
        return false
    }

}