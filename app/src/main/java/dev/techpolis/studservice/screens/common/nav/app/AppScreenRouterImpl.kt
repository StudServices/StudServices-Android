package dev.techpolis.studservice.screens.common.nav.app

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.ncapdevi.fragnav.FragNavController
import com.ncapdevi.fragnav.FragNavTransactionOptions
import dev.techpolis.studservice.R
import dev.techpolis.studservice.di.qual.ActivityBundle
import dev.techpolis.studservice.di.qual.ActivityFragmentManager
import dev.techpolis.studservice.di.scope.ActivityScope
import dev.techpolis.studservice.screens.auth.signin.SignInFragment
import dev.techpolis.studservice.screens.auth.signup.SignUpFragment
import dev.techpolis.studservice.screens.main.MainFragment
import javax.inject.Inject

@ActivityScope
class AppScreenRouterImpl @Inject constructor(
    @ActivityFragmentManager fragmentManager: FragmentManager,
    @ActivityBundle savedInstanceState: Bundle?
) : AppScreenRouter {

    companion object {
        const val TAG = "AppScreenRouterImpl"
        const val INDEX_AUTH = FragNavController.TAB1
        const val INDEX_MAIN = FragNavController.TAB2
    }

    private val fragNavController: FragNavController =
        FragNavController(fragmentManager, R.id.activity_container)

    init {
        fragNavController.apply {
            rootFragmentListener = this@AppScreenRouterImpl
            defaultTransactionOptions = FragNavTransactionOptions
                .newBuilder()
                .customAnimations(
                    R.anim.fade_in,
                    R.anim.fade_out,
                    R.anim.fade_in,
                    R.anim.fade_out,
                )
                .build()
            initialize(INDEX_AUTH, savedInstanceState)
        }

    }

    override val numberOfRootFragments: Int
        get() = 2

    override fun getRootFragment(index: Int): Fragment {
        return when (index) {
            INDEX_AUTH -> SignInFragment.newInstance()
            INDEX_MAIN -> MainFragment.newInstance()
            else -> throw IllegalStateException("Need to send an index that we know")
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        fragNavController.onSaveInstanceState(outState)
    }

    override fun navigateUp() {
        fragNavController.popFragment()
    }

    override fun toSignIn() {
        fragNavController.pushFragment(SignInFragment.newInstance())
    }

    override fun toSignUp() {
        fragNavController.pushFragment(SignUpFragment.newInstance())
    }

    override fun toAuth() {
        fragNavController.switchTab(INDEX_AUTH)
    }

    override fun toMain() {
        fragNavController.switchTab(INDEX_MAIN)
    }
}
