package dev.techpolis.studservice.screens.main.userservices

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dev.techpolis.studservice.R
import dev.techpolis.studservice.screens.common.mvp.MvpViewObservableBase
import dev.techpolis.studservice.screens.main.services.ServicesMvpViewImpl
import dev.techpolis.studservice.screens.main.userservices.offers.UserServiceOffersFragment
import dev.techpolis.studservice.screens.main.userservices.requests.UserServiceRequestsFragment

class UserServicesMvpViewImpl(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?,
    fragment: Fragment,
) : MvpViewObservableBase<UserServicesMvpView.Listener>(), UserServicesMvpView {

    override var rootView: View =
        layoutInflater.inflate(R.layout.fragment_main__user_services, parent, false)
    private val viewPager: ViewPager2 = findViewById(R.id.fragment_main__user_services_pager)
    private val tabLayout: TabLayout = findViewById(R.id.fragment_main__user_services_tab_layout)

    init {
        viewPager.adapter = ViewPagerAdapter(fragment)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "My Offers"
                1 -> tab.text = "My Requests"
            }
        }.attach()
    }

    class ViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
        override fun getItemCount() = 2

        override fun createFragment(position: Int): Fragment =
            when (position) {
                0 -> UserServiceOffersFragment.newInstance()
                1 -> UserServiceRequestsFragment.newInstance()
                else -> throw IllegalStateException("Need to send an index that we know")
            }
    }
}