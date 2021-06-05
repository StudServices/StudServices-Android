package dev.techpolis.studservice.screens.auth.signin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.widget.doOnTextChanged
import dev.techpolis.studservice.R
import dev.techpolis.studservice.screens.common.mvp.MvpViewObservableBase


class SignInMvpViewImpl (
    layoutInflater: LayoutInflater,
    parent: ViewGroup?
) : MvpViewObservableBase<SignInMvpView.Listener>(), SignInMvpView {
    override var rootView: View =
        layoutInflater.inflate(R.layout.fragment_auth__sign_in, parent, false)

    private val etUsername: AppCompatEditText = findViewById(R.id.fragment_auth__sign_in__login)
    private val etPassword: AppCompatEditText = findViewById(R.id.fragment_auth__sign_in__password)
    private val ibSignIn: AppCompatButton = findViewById(R.id.fragment_auth__sign_in__button)
    private val ibGoogleAuth: AppCompatImageButton = findViewById(R.id.fragment_auth__google_sign_in_button)
    private val tvHaveNotAcc: AppCompatTextView = findViewById(R.id.fragment_auth__sign_in__link)

    init {
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

        tvHaveNotAcc.setOnClickListener {
            listeners.forEach {
                it.onHaveNotAccTvClicked()
            }
        }

        ibSignIn.setOnClickListener {
            listeners.forEach {
                it.onSignInBtnClicked(
                    username = etUsername.text.toString(),
                    password = etPassword.text.toString()
                )
            }
        }

        ibGoogleAuth.setOnClickListener{
            listeners.forEach{
                it.onGoogleAuthBtnClicked()
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
        TODO("Not yet implemented")
    }

    override fun showPasswordFieldError(msgId: Int) {
        TODO("Not yet implemented")
    }

    override fun hideUsernameFieldError() {
        TODO("Not yet implemented")
    }

    override fun hidePasswordFieldError() {
        TODO("Not yet implemented")
    }

    override fun setStateSignInButton(isEnabled: Boolean) {
        ibSignIn.isEnabled = isEnabled
    }

    override fun unsuccessAuth() {
        Toast.makeText(this.context, "Unsuccessful authorization", Toast.LENGTH_SHORT).show()
    }
}