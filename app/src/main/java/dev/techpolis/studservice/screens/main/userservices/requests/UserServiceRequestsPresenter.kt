package dev.techpolis.studservice.screens.main.userservices.requests

import android.util.Log
import dev.techpolis.studservice.data.Status
import dev.techpolis.studservice.data.entities.ServiceEntity
import dev.techpolis.studservice.interactors.ServiceInteractor
import dev.techpolis.studservice.providers.ServiceProvider
import dev.techpolis.studservice.screens.common.mvp.MvpPresenter
import dev.techpolis.studservice.screens.common.nav.BackPressDispatcher
import dev.techpolis.studservice.screens.common.nav.main.MainScreenRouter
import kotlinx.coroutines.*

class UserServiceRequestsPresenter(
    private val serviceInteractor: ServiceInteractor,
    private val serviceProvider: ServiceProvider,
    private val mainScreenRouter: MainScreenRouter,
    private val backPressDispatcher: BackPressDispatcher,
): MvpPresenter<UserServiceRequestsMvpView>, UserServiceRequestsMvpView.Listener {

    private lateinit var view: UserServiceRequestsMvpView
    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    override fun bindView(view: UserServiceRequestsMvpView) {
        this.view = view
    }

    private fun initData() {
        coroutineScope.launch {
            val services = serviceInteractor.getUserServices(2)
            if (services.status is Status.Success) {
                view.bindData(services.data!!)
            }
        }
    }

    override fun onStart() {
        view.registerListener(this)
        backPressDispatcher.registerListener(this)
        initData()
    }

    override fun onStop() {
        view.unregisterListener(this)
        backPressDispatcher.unregisterListener(this)
    }

    override fun onDestroy() {
        coroutineScope.coroutineContext.cancelChildren()
    }

    override fun onServiceRequestClicked(service: ServiceEntity) {
        serviceProvider.service = service
        mainScreenRouter.toServiceInfo()
    }

    override fun onBackPressed(): Boolean {
        Log.e("UserServiceRequest", "NavigateUp")
        return false
    }
}