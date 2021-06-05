package dev.techpolis.studservice.screens.main.services

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
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

    private val etSearch: AppCompatEditText =
        findViewById(R.id.fragment_main_services__search_edit_text)

    private val selectedColor = getColorStateList(R.color.text_black)
    private val unselectedColor = getColorStateList(R.color.text_gray)

    init {
        viewPager.adapter = ViewPagerAdapter(fragment)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            val tabContainer = LayoutInflater.from(context)
                .inflate(R.layout.custom_tab_item, tabLayout, false) as ViewGroup?
            if (tabContainer != null) {
                val textView =
                    tabContainer.findViewById<AppCompatTextView>(R.id.custom_tab_item__tv)
                when (position) {
                    0 -> {
                        textView.text = getString(R.string.offers_tab_title)
                        textView.makeSelectedStyle()
                    }
                    1 -> {
                        textView.text = getString(R.string.requests_tab_title)
                        textView.makeUnselectedStyle()
                    }
                    else -> {
                    }
                }
                tab.customView = tabContainer
            }
        }.attach()


        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            private fun TabLayout.Tab.getTextView(): AppCompatTextView? {
                val customView: View = customView ?: return null
                return customView.findViewById(R.id.custom_tab_item__tv)
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                val textView = tab?.getTextView() ?: return
                textView.makeSelectedStyle()
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                val textView = tab?.getTextView() ?: return
                textView.makeUnselectedStyle()
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {}

        })

        etSearch.setOnFocusChangeListener { _, b ->
            if (b) {
                listeners.forEach { it.onSearchViewFocus() }
            }
        }
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

    private fun AppCompatTextView.makeSelectedStyle() {
        textSize = 20f
        setTextColor(selectedColor)
    }

    private fun AppCompatTextView.makeUnselectedStyle() {
        textSize = 18f
        setTextColor(unselectedColor)
    }

    private fun addTabWithText(text: String) {
        val tabContainer = LayoutInflater.from(context)
            .inflate(R.layout.custom_tab_item, tabLayout, false) as ViewGroup?
        if (tabContainer != null) {
            val textView =
                tabContainer.findViewById<AppCompatTextView>(R.id.custom_tab_item__tv)
            val newTab = tabLayout.newTab()
            textView.text = text
            newTab.customView = tabContainer
            tabLayout.addTab(newTab)
        }
    }

}

