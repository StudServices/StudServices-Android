package dev.techpolis.studservice.common.base

import androidx.fragment.app.Fragment
import android.os.Bundle
import dev.techpolis.studservice.common.mvp.factory.MvpViewFactory
import dev.techpolis.studservice.common.mvp.factory.PresenterFactory
import dev.techpolis.studservice.common.nav.app.AppScreenRouter
import dev.techpolis.studservice.di.activity.ActivityComponent
import javax.inject.Inject

abstract class BaseFragment : Fragment() {

//    lateinit var drawerController: DrawerController

    protected val activityComponent: ActivityComponent
        get() = (requireActivity() as BaseActivity).activityComponent

    protected val appScreenNavigator: AppScreenRouter
        get() = (requireActivity() as BaseActivity).appScreenRouter

    @Inject
    lateinit var mvpViewFactory: MvpViewFactory

    @Inject
    lateinit var presenterFactory: PresenterFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent.inject(this)
//        drawerController = (activity as MainActivity).drawerController
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        appScreenNavigator.onSaveInstanceState(outState)
    }
}