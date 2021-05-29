package dev.techpolis.studservice.screens.common.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.techpolis.studservice.StudServicesApp
import dev.techpolis.studservice.screens.common.nav.BackPressDispatcher
import dev.techpolis.studservice.screens.common.nav.BackPressedListener
import dev.techpolis.studservice.screens.common.nav.app.AppScreenRouterImpl
import dev.techpolis.studservice.di.component.ActivityComponent
import dev.techpolis.studservice.screens.common.nav.app.AppScreenRouter
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity(), BackPressDispatcher {

    lateinit var activityComponent: ActivityComponent
        private set

    private val backPressedListeners: MutableSet<BackPressedListener> = HashSet()

    @Inject
    lateinit var appScreenRouter: AppScreenRouter

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