package dev.techpolis.studservice.screens.common.base

import androidx.fragment.app.Fragment
import android.os.Bundle
import dev.techpolis.studservice.screens.common.mvp.factory.MvpViewFactory
import dev.techpolis.studservice.screens.common.mvp.factory.PresenterFactory
import dev.techpolis.studservice.screens.common.nav.app.AppScreenRouterImpl
import dev.techpolis.studservice.di.component.ActivityComponent
import dev.techpolis.studservice.repositories.service.local.LocalServicesRepo
import dev.techpolis.studservice.screens.common.nav.app.AppScreenRouter
import javax.inject.Inject

abstract class BaseFragment : Fragment() {

    protected val activityComponent: ActivityComponent
        get() = (requireActivity() as BaseActivity).activityComponent

    protected val appScreenRouter: AppScreenRouter
        get() = (requireActivity() as BaseActivity).appScreenRouter

    @Inject
    lateinit var mvpViewFactory: MvpViewFactory

    @Inject
    lateinit var presenterFactory: PresenterFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent.inject(this)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        appScreenRouter.onSaveInstanceState(outState)
    }
}