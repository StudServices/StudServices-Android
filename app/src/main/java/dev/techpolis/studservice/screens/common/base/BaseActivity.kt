package dev.techpolis.studservice.screens.common.base

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import dev.techpolis.studservice.StudServicesApp
import dev.techpolis.studservice.screens.common.nav.BackPressDispatcher
import dev.techpolis.studservice.screens.common.nav.BackPressedListener
import dev.techpolis.studservice.screens.common.nav.app.AppScreenRouterImpl
import dev.techpolis.studservice.di.component.ActivityComponent
import dev.techpolis.studservice.screens.common.nav.app.AppScreenRouter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity(), BackPressDispatcher {

    lateinit var activityComponent: ActivityComponent
        private set

    private val backPressedListeners: ArrayDeque<BackPressedListener> = ArrayDeque()

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
        backPressedListeners.addFirst(listener)
    }

    override fun unregisterListener(listener: BackPressedListener) {
        backPressedListeners.remove(listener)
    }

    override fun onBackPressed() {
        for (listener in backPressedListeners) {
            if (listener.onBackPressed()) return
        }
        super.onBackPressed()
    }
}