package dev.techpolis.studservice.common.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import javax.inject.Inject

abstract class BaseFragment : Fragment() {

//    lateinit var drawerController: DrawerController

    protected val activityComponent: ActivityComponent
        get() = (requireActivity() as BaseActivity).activityComponent

    protected val appScreenNavigator: AppScreenNavigator
        get() = (requireActivity() as BaseActivity).appScreenNavigator

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