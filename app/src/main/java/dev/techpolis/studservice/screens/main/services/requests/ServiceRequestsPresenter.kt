package dev.techpolis.studservice.screens.main.services.requests

import android.util.Log
import dev.techpolis.studservice.data.Status
import dev.techpolis.studservice.data.entities.ServiceEntity
import dev.techpolis.studservice.data.model.ServiceTypeEnum
import dev.techpolis.studservice.interactors.ServiceInteractor
import dev.techpolis.studservice.providers.ServiceInfoProvider
import dev.techpolis.studservice.screens.common.mvp.MvpPresenter
import dev.techpolis.studservice.screens.common.nav.BackPressDispatcher
import dev.techpolis.studservice.screens.common.nav.main.MainScreenRouter
import kotlinx.coroutines.*

class ServiceRequestsPresenter(
    private val serviceInteractor: ServiceInteractor,
    private val serviceInfoProvider: ServiceInfoProvider,
    private val mainScreenRouter: MainScreenRouter,
    private val backPressDispatcher: BackPressDispatcher,
) : MvpPresenter<ServiceRequestsMvpView>, ServiceRequestsMvpView.Listener {

    private lateinit var view: ServiceRequestsMvpView
    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    override fun bindView(view: ServiceRequestsMvpView) {
        this.view = view
        initData()
    }

    private fun initData() {
        coroutineScope.launch {
            Log.e("ServicesReqPresenter", "INIT DATA")
            val services = serviceInteractor.getServices(type = ServiceTypeEnum.REQUEST)
            if (services.status is Status.Success) {
                view.bindData(services.data!!)
            }
        }
    }

    override fun onStart() {
        view.registerListener(this)
        backPressDispatcher.registerListener(this)
    }

    override fun onStop() {
        view.unregisterListener(this)
        backPressDispatcher.unregisterListener(this)
    }

    override fun onDestroy() {
        coroutineScope.coroutineContext.cancelChildren()
    }

    override fun onServiceClicked(service: ServiceEntity) {
        serviceInfoProvider.service = service
        mainScreenRouter.toServiceInfo()
    }

    override fun onPullToRefresh() {
        initData()
        coroutineScope.launch {
            delay(2000)
            view.setRefreshed()
        }
    }

    override fun onBackPressed(): Boolean {
        Log.e("ServiceRequestPresenter", "NavigateUp")
        return false
    }
}