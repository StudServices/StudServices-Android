package dev.techpolis.studservice.interactors

import android.content.Intent
import android.util.Log
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import dev.techpolis.studservice.data.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GoogleAuthInteractor @Inject constructor(private val signInClient: GoogleSignInClient) {

    companion object {
        private const val TAG = "AuthenticationService"
    }

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    fun getSignInIntent(): Intent {
        return signInClient.signInIntent
    }

    fun getAccountFromIntent(data: Intent?): Resource<GoogleSignInAccount> =
        try {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            val account = task.getResult(ApiException::class.java)!!
            Resource.success(account)
        } catch (e: ApiException) {
            Resource.error(e)
        }

    fun signOut() {
        firebaseAuth.signOut()
    }

    fun firebaseAuthWithGoogle(
        idToken: String,
        listener: OnCompleteListener<AuthResult>
    ): Resource<AuthCredential> =
        try {
            val credential = GoogleAuthProvider.getCredential(idToken, null)
            firebaseAuth.signInWithCredential(credential).addOnCompleteListener(listener)
            Resource.success(credential)
        } catch (e: ApiException) {
            Log.e(TAG, "Google sign in failed", e)
            Resource.error(e)
        }
}
