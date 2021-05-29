package dev.techpolis.studservice.screens.main.services.requests

import android.util.Log
import dev.techpolis.studservice.data.Status
import dev.techpolis.studservice.data.entities.ServiceEntity
import dev.techpolis.studservice.interactors.ServiceInteractor
import dev.techpolis.studservice.providers.ServiceProvider
import dev.techpolis.studservice.screens.common.mvp.MvpPresenter
import dev.techpolis.studservice.screens.common.nav.BackPressDispatcher
import dev.techpolis.studservice.screens.common.nav.main.MainScreenRouter
import kotlinx.coroutines.*

class ServiceRequestsPresenter(
    private val serviceInteractor: ServiceInteractor,
    private val serviceProvider: ServiceProvider,
    private val mainScreenRouter: MainScreenRouter,
    private val backPressDispatcher: BackPressDispatcher,
) : MvpPresenter<ServiceRequestsMvpView>, ServiceRequestsMvpView.Listener {

    private lateinit var view: ServiceRequestsMvpView
    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    override fun bindView(view: ServiceRequestsMvpView) {
        this.view = view
    }

    private fun initData() {
        coroutineScope.launch {
            val services = serviceInteractor.getServices()
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

    override fun onServiceClicked(service: ServiceEntity) {
        serviceProvider.service = service
        mainScreenRouter.toServiceInfo()
    }

    override fun onBackPressed(): Boolean {
        Log.e("ServiceRequestPresenter", "NavigateUp")
        return false
    }
}