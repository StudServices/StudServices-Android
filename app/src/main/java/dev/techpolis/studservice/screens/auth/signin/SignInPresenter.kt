package dev.techpolis.studservice.screens.auth.signin

import android.content.Intent
import androidx.activity.result.ActivityResult
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import dev.techpolis.studservice.R
import dev.techpolis.studservice.data.Status
import dev.techpolis.studservice.interactors.AuthInteractor
import dev.techpolis.studservice.interactors.GoogleAuthInteractor
import dev.techpolis.studservice.providers.UserProvider
import dev.techpolis.studservice.screens.common.mvp.MvpPresenter
import dev.techpolis.studservice.screens.common.nav.BackPressDispatcher
import dev.techpolis.studservice.screens.common.nav.app.AppScreenRouter
import dev.techpolis.studservice.utils.isValidEmail
import dev.techpolis.studservice.utils.isValidPassword


class SignInPresenter(
    private val appScreenRouter: AppScreenRouter,
    private val backPressDispatcher: BackPressDispatcher,
    private val authInteractor: AuthInteractor,
    private val userProvider: UserProvider,
    private val loginIntentListener: LoginIntentListener,
    private val googleAuthInteractor: GoogleAuthInteractor,
) : MvpPresenter<SignInMvpView>, SignInMvpView.Listener {

    private lateinit var view: SignInMvpView

    override fun bindView(view: SignInMvpView) {
        this.view = view
        view.setStateSignInButton(false)
    }

    fun handleResult(result: ActivityResult) {
        val accountFromResource = googleAuthInteractor.getAccountFromIntent(result.data)
        if (accountFromResource.status is Status.Success) {
            val listener =
                OnCompleteListener<AuthResult> { resultListener ->
                    if (resultListener.isSuccessful) {
                        userProvider.userId = resultListener.result!!.additionalUserInfo!!.providerId!!
                        appScreenRouter.toMain()
                    } else {
                        view.unsuccessAuth()
                    }
                }
            googleAuthInteractor.firebaseAuthWithGoogle(
                accountFromResource.data!!.idToken!!,
                listener
            )
        }
    }

    override fun onStart() {
        //TODO: Check user autentificate
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
        if (!isValidEmail(username) || !isValidPassword(password)) {
            return
        }

        val listener =
            OnCompleteListener<AuthResult> { result ->
                if (result.isSuccessful) {
                    userProvider.userId = result.result?.additionalUserInfo?.providerId ?: "2"
                    appScreenRouter.toMain()
                } else {
                    view.unsuccessAuth()
                }
            }
        authInteractor.signInWithEmailAndPassword(username, password, listener)
    }

    override fun onGoogleAuthBtnClicked() {
        val signInIntent: Intent = googleAuthInteractor.getSignInIntent()
        loginIntentListener.launchLoginForResult(signInIntent)

    }

    override fun onForgotPasswordTvClicked() {
//        TODO("Not yet implemented")
    }

    override fun onHaveNotAccTvClicked() {
        appScreenRouter.toSignUp()
    }

    override fun onUsernameFieldTextChanged(text: String) {
        if (!isValidEmail(text)) {
            view.setStateSignInButton(false)
            view.showUsernameFieldError(R.string.incorrect_email)
        } else {
            view.hideUsernameFieldError()
            view.setStateSignInButton(true)
        }
    }

    override fun onPasswordFieldTextChanged(text: String) {
        if (!isValidPassword(text)) {
            view.setStateSignInButton(false)
            view.showPasswordFieldError(R.string.incorrect_username)
        } else {
            view.hidePasswordFieldError()
            view.setStateSignInButton(true)
        }
    }

    override fun onBackPressed(): Boolean {
        return false
    }


}
