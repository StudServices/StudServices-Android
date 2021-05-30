package dev.techpolis.studservice.interactors

import android.util.Log
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import dev.techpolis.studservice.data.Status
import dev.techpolis.studservice.repositories.user.UserRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.internal.wait
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthInteractor @Inject constructor(
    private val userRepo: UserRepo
) {
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

    fun signUpWithEmailAndPassword(email: String, password: String, listener: OnCompleteListener<AuthResult>) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(listener)
    }

    fun signInWithEmailAndPassword(email: String, password: String, listener: OnCompleteListener<AuthResult>) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(listener)
    }

}