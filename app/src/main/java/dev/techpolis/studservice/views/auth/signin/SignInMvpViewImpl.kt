package dev.techpolis.studservice.views.auth.signin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatTextView
import dev.techpolis.studservice.R
import dev.techpolis.studservice.common.mvp.MvpViewObservableBase

class SignInMvpViewImpl (
    layoutInflater: LayoutInflater,
    parent: ViewGroup?
) : MvpViewObservableBase<SignInMvpView.Listener>(), SignInMvpView {
    override var rootView: View =
        layoutInflater.inflate(R.layout.fragment_auth__sign_in, parent, false)

  //  private val pbLoading: ProgressBar = findViewById(R.id.fragment_signin__loading)
    private val etUsername: AppCompatEditText = findViewById(R.id.fragment_auth__sign_in__login)
    private val etPassword: AppCompatEditText = findViewById(R.id.fragment_auth__sign_in__password)
    private val ibSignIn: AppCompatButton = findViewById(R.id.fragment_auth__sign_in__button)
    private val tvHaveNotAcc: AppCompatTextView = findViewById(R.id.fragment_auth__sign_in__link)


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
}