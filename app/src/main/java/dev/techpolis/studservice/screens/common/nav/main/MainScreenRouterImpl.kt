package dev.techpolis.studservice.screens.common.nav.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.ncapdevi.fragnav.FragNavController
import com.ncapdevi.fragnav.FragNavTransactionOptions
import dev.techpolis.studservice.R
import dev.techpolis.studservice.di.qual.MainBundle
import dev.techpolis.studservice.di.qual.MainFragmentManager
import dev.techpolis.studservice.di.scope.MainScope
import dev.techpolis.studservice.screens.main.MainMvpView
import dev.techpolis.studservice.screens.main.search.SearchFragment
import dev.techpolis.studservice.screens.main.service_info.ServiceInfoFragment
import dev.techpolis.studservice.screens.main.services.ServicesFragment
import dev.techpolis.studservice.screens.main.user_services.UserServicesFragment
import dev.techpolis.studservice.screens.main.datepicker.DatePickerFragment
import dev.techpolis.studservice.screens.main.my_profile.MyProfileFragment
import dev.techpolis.studservice.screens.main.profile.ProfileFragment
import dev.techpolis.studservice.screens.main.user_services.new.NewServiceFragment
import javax.inject.Inject

@MainScope
class MainScreenRouterImpl @Inject constructor(
    @MainFragmentManager fragmentManager: FragmentManager,
    @MainBundle savedInstanceState: Bundle?,
) : MainScreenRouter {

    companion object {
        const val TAG = "MainScreenRouterImpl"
        const val INDEX_MY_PROFILE = FragNavController.TAB1
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
            INDEX_MY_PROFILE -> MyProfileFragment.newInstance()
            INDEX_SERVICES -> ServicesFragment.newInstance()
            INDEX_USER_SERVICES -> UserServicesFragment.newInstance()
            else -> throw IllegalStateException("Need to send an index that we know")
        }
    }

    override fun navigateUp() {
        fragNavController.popFragment()
        if (fragNavController.isRootFragment) {
            bottomBar.isBottomBarVisible(true)
            when (fragNavController.currentStackIndex) {
                INDEX_USER_SERVICES -> bottomBar.bottomBarSetSelected(R.id.menu_item__userServices)
                INDEX_MY_PROFILE -> bottomBar.bottomBarSetSelected(R.id.menu_item__my_profile)
                INDEX_SERVICES -> bottomBar.bottomBarSetSelected(R.id.menu_item__services)
            }
        }

    }

    override fun toMyProfile() {
        bottomBar.isBottomBarVisible(true)
        fragNavController.switchTab(INDEX_MY_PROFILE)
    }

    override fun toProfile() {
        fragNavController.pushFragment(ProfileFragment.newInstance())
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

    override fun toSearchScreen() {
        bottomBar.isBottomBarVisible(false)
        fragNavController.pushFragment(SearchFragment.newInstance())
    }

    override fun toNewService() {
        bottomBar.isBottomBarVisible(false)
        fragNavController.pushFragment(NewServiceFragment.newInstance())
    }

    override fun toDatePicker() {
        bottomBar.isBottomBarVisible(false)
        fragNavController.pushFragment(DatePickerFragment.newInstance())
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