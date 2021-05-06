package dev.techpolis.studservice.views.auth.signup

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatTextView
import dev.techpolis.studservice.R
import dev.techpolis.studservice.common.mvp.MvpViewObservableBase

class SignUpMvpViewImpl(
    inflater: LayoutInflater,
    parent: ViewGroup?
) : MvpViewObservableBase<SignUpMvpView.Listener>(), SignUpMvpView {
    override var rootView: View = inflater.inflate(R.layout.fragment_auth__sign_up, parent, false)

 //   private val pbLoading: ProgressBar = findViewById(R.id.fragment_signup__loading)
    private val etUsername: AppCompatEditText = findViewById(R.id.fragment_auth__sign_up__nickname)
    private val etPassword: AppCompatEditText = findViewById(R.id.fragment_auth__sign_up__password)
    private val etPasswordAgain: AppCompatEditText = findViewById(R.id.fragment_auth__sign_up__password_again)
    private val etEmail: AppCompatEditText = findViewById(R.id.fragment_auth__sign_up__email)
    private val ibSignUp: AppCompatImageButton = findViewById(R.id.fragment_auth__sign_up__button)



    override fun showLoading() {
        TODO("Not yet implemented")
    }

    override fun hideLoading() {
        TODO("Not yet implemented")
    }

    override fun showError(msgId: Int) {
        TODO("Not yet implemented")
    }

    override fun showUsernameFieldError(msgId: Int) {
        TODO("Not yet implemented")
    }

    override fun showEmailFieldError(msgId: Int) {
        TODO("Not yet implemented")
    }

    override fun showPasswordFieldError(msgId: Int) {
        TODO("Not yet implemented")
    }

    override fun hideUsernameFieldError() {
        TODO("Not yet implemented")
    }

    override fun hideEmailFieldError() {
        TODO("Not yet implemented")
    }

    override fun hidePasswordFieldError() {
        TODO("Not yet implemented")
    }
}