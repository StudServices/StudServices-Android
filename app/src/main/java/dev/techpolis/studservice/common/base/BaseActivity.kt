package dev.techpolis.studservice.common.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.techpolis.studservice.StudServicesApp
import dev.techpolis.studservice.common.mvp.factory.MvpViewFactory
import dev.techpolis.studservice.common.mvp.factory.PresenterFactory
import dev.techpolis.studservice.common.nav.BackPressDispatcher
import dev.techpolis.studservice.common.nav.BackPressedListener
import dev.techpolis.studservice.common.nav.app.AppScreenRouterImpl
import dev.techpolis.studservice.di.activity.ActivityComponent
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity(), BackPressDispatcher {

    lateinit var activityComponent: ActivityComponent
        private set

    private val backPressedListeners: MutableSet<BackPressedListener> = HashSet()

    @Inject lateinit var appScreenRouter: AppScreenRouterImpl

    @Inject
    lateinit var presenterFactory: PresenterFactory
    @Inject
    lateinit var mvpViewFactory: MvpViewFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        activityComponent = (application as StudServicesApp).appComponent
            .newActivityComponentBuilder()
            .activity(this)
            .savedInstanceState(savedInstanceState)
            .build()
        super.onCreate(savedInstanceState)
        activityComponent.inject(this)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        appScreenRouter.onSaveInstanceState(outState)
    }

    override fun registerListener(listener: BackPressedListener) {
        backPressedListeners.add(listener);
    }

    override fun unregisterListener(listener: BackPressedListener) {
        backPressedListeners.remove(listener)
    }

    override fun onBackPressed() {
        var isBackPressConsumedByAnyListener = false
        backPressedListeners.forEach { listener ->
            if (listener.onBackPressed()) {
                isBackPressConsumedByAnyListener = true
            }
        }
        if (!isBackPressConsumedByAnyListener) {
            super.onBackPressed()
        }
    }
}