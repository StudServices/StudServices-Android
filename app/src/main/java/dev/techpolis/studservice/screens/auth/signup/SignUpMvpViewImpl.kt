package dev.techpolis.studservice.screens.auth.signup

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.widget.doOnTextChanged
import dev.techpolis.studservice.R
import dev.techpolis.studservice.screens.common.mvp.MvpViewObservableBase

class SignUpMvpViewImpl(
    inflater: LayoutInflater,
    parent: ViewGroup?
) : MvpViewObservableBase<SignUpMvpView.Listener>(), SignUpMvpView {

    override var rootView: View = inflater.inflate(R.layout.fragment_auth__sign_up, parent, false)

    //   private val pbLoading: ProgressBar = findViewById(R.id.fragment_signup__loading)
    private val etUsername: AppCompatEditText = findViewById(R.id.fragment_auth__sign_up__nickname)
    private val etPassword: AppCompatEditText = findViewById(R.id.fragment_auth__sign_up__password)
    private val etEmail: AppCompatEditText = findViewById(R.id.fragment_auth__sign_up__email)
    private val buttonSignUp: AppCompatButton = findViewById(R.id.fragment_auth__sign_up__button)
    private val tvHaveAcc: AppCompatTextView = findViewById(R.id.fragment_auth__sign_up__link)

    init {
        tvHaveAcc.setOnClickListener { listeners.forEach { it.onHaveAccClicked() } }

        etUsername.doOnTextChanged { text, _, _, _ ->
            listeners.forEach {
                it.onUsernameFieldTextChanged(text.toString())
            }
        }

        etPassword.doOnTextChanged { text, _, _, _ ->
            listeners.forEach {
                it.onPasswordFieldTextChanged(text.toString())
            }
        }

        etEmail.doOnTextChanged { text, _, _, _ ->
            listeners.forEach {
                it.onEmailFieldTextChanged(text.toString())
            }
        }

        buttonSignUp.setOnClickListener {
            listeners.forEach {
                it.onSignUpBtnClicked(
                    username = etUsername.text.toString(),
                    email = etEmail.text.toString(),
                    password = etPassword.text.toString()
                )
            }
        }
    }


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
        etUsername.error = getString(msgId)
    }

    override fun showEmailFieldError(msgId: Int) {
        etEmail.error = getString(msgId)
    }

    override fun showPasswordFieldError(msgId: Int) {
        etPassword.error = getString(msgId)
    }

    override fun hideUsernameFieldError() {
        etUsername.error = null
    }

    override fun hideEmailFieldError() {
        etEmail.error = null
    }

    override fun hidePasswordFieldError() {
        etPassword.error = null
    }

    override fun setStateSignUpButton(isEnabled: Boolean) {
        buttonSignUp.isEnabled = isEnabled
    }

    override fun unsuccessSignUp() {
        Toast.makeText(this.context, "Unsuccessful registration", Toast.LENGTH_SHORT).show()
    }
}