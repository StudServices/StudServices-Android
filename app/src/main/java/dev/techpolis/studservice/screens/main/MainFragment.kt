package dev.techpolis.studservice.screens.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dev.techpolis.studservice.di.component.MainComponent
import dev.techpolis.studservice.screens.common.base.BaseFragment
import dev.techpolis.studservice.screens.common.nav.main.MainScreenRouter
import javax.inject.Inject

class MainFragment : BaseFragment() {

    lateinit var mainComponent: MainComponent
        private set

    private lateinit var presenter: MainPresenter

    @Inject
    lateinit var mainScreenRouter: MainScreenRouter

    override fun onCreate(savedInstanceState: Bundle?) {
        mainComponent = activityComponent
            .newMainComponentBuilder()
            .mainFragment(this)
            .savedInstanceState(savedInstanceState)
            .build()
        super.onCreate(savedInstanceState)
        mainComponent.inject(this)

        presenterFactory.mainScreenRouter = mainScreenRouter
        presenter = presenterFactory.createMainPresenter()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mainScreenRouter.onSaveInstanceState(outState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: MainMvpView = mvpViewFactory.createMainMvpView(container)
        presenter.bindView(view)
        return view.rootView
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart()
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    companion object {
        fun newInstance(): Fragment {
            return MainFragment()
        }
    }
}
