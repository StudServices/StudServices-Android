package dev.techpolis.studservice.screens.main.services

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
import dev.techpolis.studservice.screens.main.services.offers.ServiceOffersFragment
import dev.techpolis.studservice.screens.main.services.requests.ServiceRequestsFragment

class ServicesMvpViewImpl(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?,
    fragment: Fragment,
) : MvpViewObservableBase<ServicesMvpView.Listener>(), ServicesMvpView {

    override var rootView: View =
        layoutInflater.inflate(R.layout.fragment_main__services, parent, false)
    private val viewPager: ViewPager2 = findViewById(R.id.fragment_main__services_pager)
    private val tabLayout: TabLayout = findViewById(R.id.fragment_main__services_tab_layout)

    init {
        viewPager.adapter = ViewPagerAdapter(fragment)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Offers"
                1 -> tab.text = "Requests"
            }
        }.attach()
    }

    class ViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
        override fun getItemCount() = 2

        override fun createFragment(position: Int): Fragment =
            when (position) {
                0 -> ServiceOffersFragment.newInstance()
                1 -> ServiceRequestsFragment.newInstance()
                else -> throw IllegalStateException("Need to send an index that we know")
            }
    }

}

