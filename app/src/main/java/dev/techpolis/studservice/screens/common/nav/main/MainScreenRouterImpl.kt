package dev.techpolis.studservice.screens.common.nav.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.ncapdevi.fragnav.FragNavController
import com.ncapdevi.fragnav.FragNavSwitchController
import com.ncapdevi.fragnav.FragNavTransactionOptions
import com.ncapdevi.fragnav.tabhistory.UniqueTabHistoryStrategy
import dev.techpolis.studservice.R
import dev.techpolis.studservice.di.qual.MainBundle
import dev.techpolis.studservice.di.qual.MainFragmentManager
import dev.techpolis.studservice.di.scope.MainScope
import dev.techpolis.studservice.screens.main.MainMvpView
import dev.techpolis.studservice.screens.main.map.MapFragment
import dev.techpolis.studservice.screens.main.profile.ProfileFragment
import dev.techpolis.studservice.screens.main.profile.settings.SettingsFragment
import dev.techpolis.studservice.screens.main.search.SearchFragment
import dev.techpolis.studservice.screens.main.serviceinfo.ServiceInfoFragment
import dev.techpolis.studservice.screens.main.services.ServicesFragment
import dev.techpolis.studservice.screens.main.userservices.UserServicesFragment
import dev.techpolis.studservice.screens.main.userservices.newservice.NewServiceFragment
import javax.inject.Inject

@MainScope
class MainScreenRouterImpl @Inject constructor(
    @MainFragmentManager fragmentManager: FragmentManager,
    @MainBundle savedInstanceState: Bundle?,
) : MainScreenRouter {

    companion object {
        const val TAG = "MainScreenRouterImpl"
        const val INDEX_PROFILE = FragNavController.TAB1
        const val INDEX_SERVICES = FragNavController.TAB2
        const val INDEX_USER_SERVICES = FragNavController.TAB3
        const val INDEX_SERVICE_INFO = FragNavController.TAB4
    }

    private lateinit var bottomBar: MainMvpView

    private val fragNavController: FragNavController =
        FragNavController(fragmentManager, R.id.fragment_main__nav_host_fragment)

    init {
        fragNavController.apply {
            rootFragmentListener = this@MainScreenRouterImpl
//            navigationStrategy = UniqueTabHistoryStrategy(object : FragNavSwitchController {
//                override fun switchTab(index: Int, transactionOptions: FragNavTransactionOptions?) {
//                    fragNavController.switchTab(index, transactionOptions)
//                }
//            })
            defaultTransactionOptions = transactionOptions(
                R.anim.fade_in,
                R.anim.fade_out,
                R.anim.fade_in,
                R.anim.fade_out,
            )
            initialize(INDEX_USER_SERVICES, savedInstanceState)
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        fragNavController.onSaveInstanceState(outState)
    }

    override fun bindBottomBarView(view: MainMvpView) {
        this.bottomBar = view
    }

    override val numberOfRootFragments: Int
        get() = 3

    override fun getRootFragment(index: Int): Fragment {
        return when (index) {
            INDEX_PROFILE -> ProfileFragment.newInstance()
            INDEX_SERVICES -> ServicesFragment.newInstance()
            INDEX_USER_SERVICES -> UserServicesFragment.newInstance()
//            INDEX_SERVICE_INFO -> ServiceInfoFragment.newInstance()
            else -> throw IllegalStateException("Need to send an index that we know")
        }
    }

    override fun navigateUp() {
        fragNavController.popFragment()
        var isVisible = true
        when (fragNavController.currentStackIndex) {
            INDEX_USER_SERVICES -> bottomBar.bottomBarSetSelected(R.id.menu_item__userServices)
            INDEX_PROFILE -> bottomBar.bottomBarSetSelected(R.id.menu_item__profile)
            INDEX_SERVICES -> bottomBar.bottomBarSetSelected(R.id.menu_item__services)
            else -> isVisible = false
        }
        bottomBar.isBottomBarVisible(isVisible)
    }

    override fun toProfile() {
        bottomBar.isBottomBarVisible(true)
        fragNavController.switchTab(INDEX_PROFILE)
    }

    override fun toProfileSettings() {
        fragNavController.pushFragment(SettingsFragment.newInstance())
    }

    override fun toServiceInfo() {
        fragNavController.pushFragment(ServiceInfoFragment.newInstance())
        bottomBar.isBottomBarVisible(false)
    }

    override fun toServices() {
        bottomBar.isBottomBarVisible(true)
        fragNavController.switchTab(INDEX_SERVICES)
    }

    override fun toUserServices() {
        bottomBar.isBottomBarVisible(true)
        fragNavController.switchTab(INDEX_USER_SERVICES)
    }

    override fun toMap() {
        fragNavController.pushFragment(MapFragment.newInstance())
    }

    override fun toSearchScreen() {
        bottomBar.isBottomBarVisible(false)
        fragNavController.pushFragment(SearchFragment.newInstance())
    }

    override fun toNewService() {
        bottomBar.isBottomBarVisible(false)
        fragNavController.pushFragment(NewServiceFragment.newInstance())
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