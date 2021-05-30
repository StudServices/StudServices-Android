package dev.techpolis.studservice.screens.auth.signin

import dev.techpolis.studservice.screens.common.mvp.MvpViewObservable
import dev.techpolis.studservice.screens.common.nav.BackPressedListener

interface SignInMvpView : MvpViewObservable<SignInMvpView.Listener> {
    interface Listener : BackPressedListener {
        fun onSignInBtnClicked(username: String, password: String)
        fun onForgotPasswordTvClicked()
        fun onHaveNotAccTvClicked()
        fun onUsernameFieldTextChanged(text: String)
        fun onPasswordFieldTextChanged(text: String)
    }

    fun showLoading()
    fun hideLoading()

    fun showError(msgId: Int)
    fun showUsernameFieldError(msgId: Int)
    fun showPasswordFieldError(msgId: Int)

    fun hideUsernameFieldError()
    fun hidePasswordFieldError()

    fun setStateSignInButton(isEnabled: Boolean)
    fun unsuccessAuth()
}