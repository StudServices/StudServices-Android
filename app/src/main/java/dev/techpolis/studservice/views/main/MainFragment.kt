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
import dev.techpolis.studservice.common.base.BaseFragment
import dev.techpolis.studservice.utils.findChildNavControllerByHostId
import dev.techpolis.studservice.views.auth.signin.SignInFragment
import dev.techpolis.studservice.views.auth.signin.SignInMvpView
import dev.techpolis.studservice.views.auth.signin.SignInPresenter

class MainFragment : BaseFragment() {

    private lateinit var bottomBar: BottomNavigationView

    private lateinit var presenter: MainPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: MainMvpView = mvpViewFactory.createMainMvpView(container)
        presenter.bindView(view)
        return view.rootView
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart()
    }

    override fun onStop() {
        presenter.onStop()
        super.onStop()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    companion object {
        fun newInstance(): Fragment = MainFragment()
    }

    private fun View.initViews() {
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
