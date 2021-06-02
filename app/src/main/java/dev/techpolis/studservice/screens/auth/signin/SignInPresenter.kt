package dev.techpolis.studservice.screens.auth.signin

import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import dev.techpolis.studservice.data.Status
import dev.techpolis.studservice.interactors.AuthInteractor
import dev.techpolis.studservice.providers.UserProvider
import dev.techpolis.studservice.screens.common.mvp.MvpPresenter
import dev.techpolis.studservice.screens.common.nav.BackPressDispatcher
import dev.techpolis.studservice.screens.common.nav.app.AppScreenRouter


class SignInPresenter(
    private val appScreenRouter: AppScreenRouter,
    private val backPressDispatcher: BackPressDispatcher,
    private val authInteractor: AuthInteractor,
    private val userProvider: UserProvider,
) : MvpPresenter<SignInMvpView>, SignInMvpView.Listener {

    private lateinit var view: SignInMvpView

    override fun bindView(view: SignInMvpView) {
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

    override fun onSignInBtnClicked(username: String, password: String) {
//        val listener =
//            OnCompleteListener<AuthResult> { result ->
//                if (result.isSuccessful) {
//                    appScreenRouter.toMain()
//                } else {
//                    view.unsuccessAuth()
//                }
//            }
//        authInteractor.signInWithEmailAndPassword(username, password, listener)
        userProvider.userId = 2
        appScreenRouter.toMain()
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
