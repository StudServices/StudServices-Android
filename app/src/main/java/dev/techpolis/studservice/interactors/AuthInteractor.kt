package dev.techpolis.studservice.interactors

import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import dev.techpolis.studservice.repositories.user.UserRepo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthInteractor @Inject constructor(
    private val userRepo: UserRepo
) {

    companion object {
        private const val TAG = "AuthInteractor"
        private const val RC_SIGN_IN = 90001
    }

    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val mAuthListener: FirebaseAuth.AuthStateListener =
        FirebaseAuth.AuthStateListener { firebaseAuth ->
            val user = firebaseAuth.currentUser
            if (user != null) {
                // User is signed in
            } else {
                // User is signed out
            }
        }

    fun authWithGoogle(idToken: String, listener: OnCompleteListener<AuthResult>) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        mAuth.signInWithCredential(credential).addOnCompleteListener(listener)
    }

    fun checkUser() : Boolean {
        val currentUser = mAuth.currentUser
        return currentUser == null
    }


    fun signUpWithEmailAndPassword(email: String, password: String, listener: OnCompleteListener<AuthResult>) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(listener)
    }

    fun signInWithEmailAndPassword(email: String, password: String, listener: OnCompleteListener<AuthResult>) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(listener)
    }

}