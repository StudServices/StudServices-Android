package dev.techpolis.studservice.views.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import dev.techpolis.studservice.R
import dev.techpolis.studservice.utils.findChildNavControllerByHostId

class MainFragment : Fragment() {

    private lateinit var mainNavController: NavController
    private lateinit var bottomBar: BottomNavigationView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    private fun View.initViews() {
        mainNavController = findChildNavControllerByHostId(R.id.fragment_main__nav_host_fragment)
        bottomBar = findViewById(R.id.fragment_main__bottom_nav_view)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.initViews()

        bottomBar.setupWithNavController(mainNavController)
        bottomBar.setOnNavigationItemSelectedListener { item ->
            mainNavController.navigate(
                when (item.itemId) {
                    R.id.menu_item__userServices -> R.id.userServicesFragment
                    R.id.menu_item__services -> R.id.servicesFragment
                    R.id.menu_item__profile -> R.id.profileFragment
                    else -> 0
                }
            )
            return@setOnNavigationItemSelectedListener true
        }
    }
}
