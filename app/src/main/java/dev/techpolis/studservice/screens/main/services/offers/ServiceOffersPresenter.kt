package dev.techpolis.studservice.screens.main.services.offers

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

class ServiceOffersPresenter(
    private val serviceInteractor: ServiceInteractor,
    private val serviceInfoProvider: ServiceInfoProvider,
    private val mainScreenRouter: MainScreenRouter,
    private val backPressDispatcher: BackPressDispatcher,
) : MvpPresenter<ServiceOffersMvpView>, ServiceOffersMvpView.Listener {

    private lateinit var view: ServiceOffersMvpView
    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    override fun bindView(view: ServiceOffersMvpView) {
        this.view = view
    }

    private fun initData() {
        coroutineScope.launch {
            val services = serviceInteractor.getServices(type = ServiceTypeEnum.OFFER)
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

    override fun onServiceOfferClicked(service: ServiceEntity) {
        serviceInfoProvider.service = service
        mainScreenRouter.toServiceInfo()
    }

    override fun onBackPressed(): Boolean {
        Log.e("ServiceOfferPresenter", "NavigateUp")
        return false
    }
}

