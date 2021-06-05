package dev.techpolis.studservice.di.module

import android.app.Application
import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import dagger.Module
import dagger.Provides
import dev.techpolis.studservice.R
import javax.inject.Singleton

@Module
object GoogleAuthModule {

    @Provides
    @Singleton
    fun getSignInOptions(application: Application): GoogleSignInOptions {
        return GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(application.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
    }

    @Provides
    @Singleton
    fun provideGoogleSignInClient(
        application: Application,
        googleSignInOptions: GoogleSignInOptions
    ): GoogleSignInClient {
        return GoogleSignIn.getClient(
            application,
            googleSignInOptions
        )
    }
}
