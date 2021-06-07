package dev.techpolis.studservice.screens.auth.signup

import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import dev.techpolis.studservice.R
import dev.techpolis.studservice.interactors.AuthInteractor
import dev.techpolis.studservice.providers.UserProvider
import dev.techpolis.studservice.screens.common.mvp.MvpPresenter
import dev.techpolis.studservice.screens.common.nav.BackPressDispatcher
import dev.techpolis.studservice.screens.common.nav.app.AppScreenRouter
import dev.techpolis.studservice.utils.isValidEmail
import dev.techpolis.studservice.utils.isValidPassword

class SignUpPresenter(
    private val appScreenRouter: AppScreenRouter,
    private val backPressDispatcher: BackPressDispatcher,
    private val authInteractor: AuthInteractor,
    private val userProvider: UserProvider,
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
        if (!isValidPassword(password) || !isValidEmail(email) || !isValidPassword(username)) {
            return
        }
        val listener =
            OnCompleteListener<AuthResult> { result ->
                if (result.isSuccessful) {
                    userProvider.userId = result.result?.additionalUserInfo?.providerId ?: "2"
                    appScreenRouter.toMain()
                } else {
                    view.unsuccessSignUp()
                }
            }
        authInteractor.signUpWithEmailAndPassword(email, password, listener)
    }

    override fun onHaveAccTvClicked() {
        appScreenRouter.toSignIn()
    }

    override fun onUsernameFieldTextChanged(text: String) {
        if (!isValidPassword(text)) {
            view.setStateSignUpButton(false)
            view.showUsernameFieldError(R.string.incorrect_username)
        } else {
            view.hideUsernameFieldError()
            view.setStateSignUpButton(true)
        }
    }

    override fun onPasswordFieldTextChanged(text: String) {
        if (!isValidPassword(text)) {
            view.setStateSignUpButton(false)
            view.showPasswordFieldError(R.string.incorrect_username)
        } else {
            view.hidePasswordFieldError()
            view.setStateSignUpButton(true)
        }
    }

    override fun onEmailFieldTextChanged(text: String) {
        if (!isValidEmail(text)) {
            view.setStateSignUpButton(false)
            view.showEmailFieldError(R.string.incorrect_email)
        } else {
            view.hideEmailFieldError()
            view.setStateSignUpButton(true)
        }
    }

    override fun onHaveAccClicked() {
        appScreenRouter.toSignIn()
    }

    override fun onBackPressed(): Boolean {
        appScreenRouter.navigateUp()
        return true
    }

}