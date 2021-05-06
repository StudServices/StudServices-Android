package dev.techpolis.studservice.common.nav.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.ncapdevi.fragnav.FragNavController
import com.ncapdevi.fragnav.FragNavSwitchController
import com.ncapdevi.fragnav.FragNavTransactionOptions
import com.ncapdevi.fragnav.tabhistory.UnlimitedTabHistoryStrategy
import dev.techpolis.studservice.R
import dev.techpolis.studservice.views.main.filters.FiltersFragment
import dev.techpolis.studservice.views.main.map.MapFragment
import dev.techpolis.studservice.views.main.profile.ProfileFragment
import dev.techpolis.studservice.views.main.profile.settings.SettingsFragment
import dev.techpolis.studservice.views.main.serviceinfo.ServiceInfoFragment
import dev.techpolis.studservice.views.main.services.ServicesFragment
import dev.techpolis.studservice.views.main.userservices.UserServicesFragment
import dev.techpolis.studservice.views.main.userservices.newservice.NewServiceFragment
import javax.inject.Inject

class MainScreenRouterImpl @Inject constructor(
    fragmentManager: FragmentManager,
    savedInstanceState: Bundle?
) : MainScreenRouter {

    companion object {
        const val TAG = "MainScreenRouterImpl"
        const val INDEX_PROFILE = FragNavController.TAB1
        const val INDEX_SERVICES = FragNavController.TAB2
        const val INDEX_USER_SERVICES = FragNavController.TAB3
    }

    private val fragNavController: FragNavController =
        FragNavController(fragmentManager, R.id.activity_container)

    val stackIsEmpty: Boolean
        get() = fragNavController.currentStack?.isEmpty() ?: true

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

    init {
        fragNavController.apply {
            rootFragmentListener = this@MainScreenRouterImpl
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
            initialize(INDEX_SERVICES, savedInstanceState)
        }

    }

    override fun toProfile() {
        fragNavController.switchTab(INDEX_PROFILE)
    }

    override fun toProfileSettings() {
        fragNavController.pushFragment(SettingsFragment.newInstance())
    }

    override fun toServiceInfo() {
        fragNavController.pushFragment(ServiceInfoFragment.newInstance())
    }

    override fun toServices() {
        fragNavController.switchTab(INDEX_SERVICES)
    }

    override fun toUserServices() {
        fragNavController.switchTab(INDEX_USER_SERVICES)
    }

    override fun toMap() {
        fragNavController.pushFragment(MapFragment.newInstance())
    }

    override fun toFilters() {
        fragNavController.pushFragment(FiltersFragment.newInstance())
    }

    override fun toNewService() {
        fragNavController.pushFragment(NewServiceFragment.newInstance())
    }

    override val numberOfRootFragments: Int
        get() = 3

    override fun getRootFragment(index: Int): Fragment {
        return when (index) {
            INDEX_PROFILE -> ProfileFragment.newInstance()
            INDEX_SERVICES -> ServicesFragment.newInstance()
            INDEX_USER_SERVICES -> UserServicesFragment.newInstance()
            else -> throw IllegalStateException("Need to send an index that we know")
        }
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

}