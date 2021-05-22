package dev.techpolis.studservice.common.nav.app

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.ncapdevi.fragnav.FragNavController
import com.ncapdevi.fragnav.FragNavSwitchController
import com.ncapdevi.fragnav.FragNavTransactionOptions
import com.ncapdevi.fragnav.tabhistory.UnlimitedTabHistoryStrategy
import dev.techpolis.studservice.R
import dev.techpolis.studservice.views.auth.signin.SignInFragment
import dev.techpolis.studservice.views.auth.signup.SignUpFragment
import dev.techpolis.studservice.views.main.MainFragment
import javax.inject.Inject

class AppScreenRouterImpl @Inject constructor(
    fragmentManager: FragmentManager,
    savedInstanceState: Bundle?
) : AppScreenRouter {

    companion object {
        const val TAG = "AppScreenRouterImpl"
        const val INDEX_AUTH = FragNavController.TAB1
        const val INDEX_MAIN = FragNavController.TAB2
    }

    private val fragNavController: FragNavController =
        FragNavController(fragmentManager, R.id.activity_container)

    val stackIsEmpty: Boolean
        get() = fragNavController.currentStack?.isEmpty() ?: true

    init {
        fragNavController.apply {
            rootFragmentListener = this@AppScreenRouterImpl
            navigationStrategy = UnlimitedTabHistoryStrategy(object : FragNavSwitchController {
                override fun switchTab(index: Int, transactionOptions: FragNavTransactionOptions?) {
                    fragNavController.switchTab(index, transactionOptions)
                }
            })
            defaultTransactionOptions = transactionOptions(
                R.anim.fade_in,
                R.anim.fade_out,
                R.anim.fade_in,
                R.anim.fade_out,
            )
//            if (appSettings.isAuthorized) {
//                initialize(INDEX_PROFILE, savedInstanceState)
//            } else {
            initialize(INDEX_AUTH, savedInstanceState)
//            }
        }

    }

    override val numberOfRootFragments: Int
        get() = 2

    override fun getRootFragment(index: Int): Fragment {
        return when (index) {
            INDEX_AUTH -> SignInFragment.newInstance()
//            INDEX_MAIN -> MainFragment.newInstance()
            else -> throw IllegalStateException("Need to send an index that we know")
        }
    }

    fun onSaveInstanceState(outState: Bundle?) {
        fragNavController.onSaveInstanceState(outState)
    }

    fun clearBackStack() {
        fragNavController.clearStack()
    }

    fun navigateUp() {
        val options = transactionOptions(
            R.anim.fade_in,
            R.anim.fade_out,
            R.anim.fade_in,
            R.anim.fade_out,
        )
        fragNavController.popFragment(options)
    }

    override fun toSignIn() {
        fragNavController.pushFragment(SignInFragment.newInstance())
    }

    override fun toSignUp() {
        fragNavController.pushFragment(SignUpFragment.newInstance())
    }

    override fun toMain() {
        fragNavController.switchTab(INDEX_MAIN)
    }

    private fun transactionOptions(vararg animationIds: Int): FragNavTransactionOptions =
        if (animationIds.size == 2)
            FragNavTransactionOptions
                .newBuilder()
                .customAnimations(
                    animationIds[0], animationIds[1]
                )
                .build()
        else
            FragNavTransactionOptions
                .newBuilder()
                .customAnimations(
                    animationIds[0], animationIds[1], animationIds[2], animationIds[3]
                )
                .build()
}
