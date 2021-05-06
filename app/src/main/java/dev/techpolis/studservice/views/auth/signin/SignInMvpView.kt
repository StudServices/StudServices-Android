package dev.techpolis.studservice.views.auth.signin

import dev.techpolis.studservice.common.mvp.MvpViewObservable
import dev.techpolis.studservice.common.nav.BackPressedListener

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
}