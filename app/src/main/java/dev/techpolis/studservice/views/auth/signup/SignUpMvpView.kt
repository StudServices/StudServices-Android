package dev.techpolis.studservice.views.auth.signup

import dev.techpolis.studservice.common.mvp.MvpViewObservable
import dev.techpolis.studservice.common.nav.BackPressedListener

interface SignUpMvpView: MvpViewObservable<SignUpMvpView.Listener> {
    interface Listener : BackPressedListener {
        fun onSignUpBtnClicked(username: String, email: String, password: String)
        fun onHaveAccTvClicked()
        fun onUsernameFieldTextChanged(text: String)
        fun onEmailFieldTextChanged(text: String)
        fun onPasswordFieldTextChanged(text: String)
        fun onPasswordCorrectionFieldTextChanged(text: String)
    }



    fun showLoading()
    fun hideLoading()

    fun showError(msgId: Int)
    fun showUsernameFieldError(msgId: Int)
    fun showEmailFieldError(msgId: Int)
    fun showPasswordFieldError(msgId: Int)

    fun hideUsernameFieldError()
    fun hideEmailFieldError()
    fun hidePasswordFieldError()

    fun setStateSignUpButton(isEnabled: Boolean)
}