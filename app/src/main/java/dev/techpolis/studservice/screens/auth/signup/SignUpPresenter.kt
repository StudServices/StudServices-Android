package dev.techpolis.studservice.screens.auth.signup

import dev.techpolis.studservice.screens.common.mvp.MvpPresenter
import dev.techpolis.studservice.screens.common.nav.BackPressDispatcher
import dev.techpolis.studservice.screens.common.nav.app.AppScreenRouter
import dev.techpolis.studservice.screens.common.nav.app.AppScreenRouterImpl

class SignUpPresenter(
    private val appScreenRouter: AppScreenRouter,
    private val backPressDispatcher: BackPressDispatcher,
) : MvpPresenter<SignUpMvpView>, SignUpMvpView.Listener {

    private lateinit var view: SignUpMvpView

    override fun bindView(view: SignUpMvpView) {
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

    override fun onSignUpBtnClicked(username: String, email: String, password: String) {
//        TODO("Not yet implemented")
    }

    override fun onHaveAccTvClicked() {
        appScreenRouter.toSignIn()
    }

    override fun onUsernameFieldTextChanged(text: String) {
//        TODO("Not yet implemented")
    }

    override fun onEmailFieldTextChanged(text: String) {
//        TODO("Not yet implemented")
    }

    override fun onPasswordFieldTextChanged(text: String) {
//        TODO("Not yet implemented")
    }

    override fun onBackPressed(): Boolean {
        appScreenRouter.navigateUp()
        return true
    }

}