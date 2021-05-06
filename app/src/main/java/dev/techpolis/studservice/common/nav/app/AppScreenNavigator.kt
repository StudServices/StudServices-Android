package dev.techpolis.studservice.common.nav.app

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.ncapdevi.fragnav.FragNavController
import com.ncapdevi.fragnav.FragNavSwitchController
import com.ncapdevi.fragnav.FragNavTransactionOptions
import com.ncapdevi.fragnav.tabhistory.UnlimitedTabHistoryStrategy
import javax.inject.Inject

class AppScreenNavigator @Inject constructor(
    fragmentManager: FragmentManager,
    savedInstanceState: Bundle?
) : AppScreenRouter, FragNavController.RootFragmentListener {

    companion object {
        const val TAG = "AppScreenNavigator"
        const val INDEX_AUTH = FragNavController.TAB1
        const val INDEX_PROFILE = FragNavController.TAB2
        const val INDEX_GROUP_LIST = FragNavController.TAB3
        const val INDEX_SETTINGS = FragNavController.TAB4
    }

    private val fragNavController: FragNavController =
        FragNavController(fragmentManager, R.id.activity_main__container)

    val stackIsEmpty: Boolean
        get() = fragNavController.currentStack?.isEmpty() ?: true

    init {
        fragNavController.apply {
            rootFragmentListener = this@AppScreenNavigator
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
            if (appSettings.isAuthorized) {
                initialize(INDEX_PROFILE, savedInstanceState)
            } else {
                initialize(INDEX_AUTH, savedInstanceState)
            }
        }

    }

    override val numberOfRootFragments: Int
        get() = 4

    override fun getRootFragment(index: Int): Fragment {
        return when (index) {
            INDEX_AUTH -> SignInFragment.newInstance()
            INDEX_PROFILE -> ProfileFragment.newInstance()
            INDEX_GROUP_LIST -> GroupListFragment.newInstance()
            INDEX_SETTINGS -> SettingsFragment.newInstance()
            else -> throw IllegalStateException("Need to send an index that we know")
        }
    }

    override fun toHome() {
        toProfile()
    }

    override fun toAuth() {
        fragNavController.switchTab(INDEX_AUTH)
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

    override fun toRecovery() {
        fragNavController.pushFragment(RecoveryFragment.newInstance())
    }

    override fun toSpotifyAuthWebView() {
        fragNavController.pushFragment(SpotifyAuthFragment.newInstance())
    }

    override fun toProfile() {
        fragNavController.switchTab(INDEX_PROFILE)
    }

    override fun toSettings() {
        fragNavController.switchTab(INDEX_SETTINGS)
    }

    override fun toGroupList() {
        fragNavController.switchTab(INDEX_GROUP_LIST)
    }

    override fun toAddGroupFragment() {
        fragNavController.pushFragment(AddGroupFragment.newInstance())
    }

    override fun toLobby(groupId: Long) {
        fragNavController.clearStack()
        fragNavController.pushFragment(LobbyFragment.newInstance(groupId))
    }

    override fun toJoinGroup() {
        fragNavController.pushFragment(JoinGroupFragment.newInstance())
    }

    override fun toNewGroup() {
        fragNavController.pushFragment(NewGroupFragment.newInstance())
    }

    override fun toShareGroup(link: String, groupId: Long) {
        fragNavController.pushFragment(ShareGroupFragment.newInstance(link, groupId))
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