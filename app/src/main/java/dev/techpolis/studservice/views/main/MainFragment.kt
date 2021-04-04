package dev.techpolis.studservice.views.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import dev.techpolis.studservice.R
import dev.techpolis.studservice.utils.findNavControllerByHostId

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavControllerByHostId(R.id.fragment_main__nav_host_fragment)
        val bottomBar = view.findViewById<BottomNavigationView>(R.id.fragment_main__bottom_nav_view)
        bottomBar?.setupWithNavController(navController)
        bottomBar?.setOnNavigationItemSelectedListener { item ->
            navController.navigate(
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
