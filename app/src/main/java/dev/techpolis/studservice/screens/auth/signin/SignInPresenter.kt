package dev.techpolis.studservice.screens.auth.signin

import android.content.Intent
import android.util.Log
import androidx.activity.result.ActivityResult
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import dev.techpolis.studservice.data.Status
import dev.techpolis.studservice.interactors.AuthInteractor
import dev.techpolis.studservice.interactors.GoogleAuthInteractor
import dev.techpolis.studservice.providers.UserProvider
import dev.techpolis.studservice.screens.common.mvp.MvpPresenter
import dev.techpolis.studservice.screens.common.nav.BackPressDispatcher
import dev.techpolis.studservice.screens.common.nav.app.AppScreenRouter


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
        val listener =
            OnCompleteListener<AuthResult> { result ->
                if (result.isSuccessful) {
                    userProvider.userId = result.result!!.additionalUserInfo!!.providerId!!
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
//        TODO("Not yet implemented")
    }

    override fun onPasswordFieldTextChanged(text: String) {
//        TODO("Not yet implemented")
    }

    override fun onBackPressed(): Boolean {
        return false
    }

}
