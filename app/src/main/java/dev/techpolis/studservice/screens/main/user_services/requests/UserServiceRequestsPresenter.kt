package dev.techpolis.studservice.screens.main.user_services.requests

import android.util.Log
import dev.techpolis.studservice.data.Status
import dev.techpolis.studservice.data.entities.ServiceEntity
import dev.techpolis.studservice.data.model.ServiceTypeEnum
import dev.techpolis.studservice.interactors.ServiceInteractor
import dev.techpolis.studservice.providers.ServiceInfoProvider
import dev.techpolis.studservice.providers.UserProvider
import dev.techpolis.studservice.screens.common.mvp.MvpPresenter
import dev.techpolis.studservice.screens.common.nav.BackPressDispatcher
import dev.techpolis.studservice.screens.common.nav.main.MainScreenRouter
import kotlinx.coroutines.*

class UserServiceRequestsPresenter(
    private val serviceInteractor: ServiceInteractor,
    private val serviceInfoProvider: ServiceInfoProvider,
    private val mainScreenRouter: MainScreenRouter,
    private val backPressDispatcher: BackPressDispatcher,
    private val userProvider: UserProvider,
): MvpPresenter<UserServiceRequestsMvpView>, UserServiceRequestsMvpView.Listener {

    private lateinit var view: UserServiceRequestsMvpView
    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    override fun bindView(view: UserServiceRequestsMvpView) {
        this.view = view
        initData()
    }

    private fun initData() {
        coroutineScope.launch {
            val services = serviceInteractor.getUserServices(userProvider.userId, ServiceTypeEnum.REQUEST)
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

    override fun onServiceRequestClicked(service: ServiceEntity) {
        serviceInfoProvider.service = service
        mainScreenRouter.toServiceInfo()
    }

    override fun onBackPressed(): Boolean {
        Log.e("UserServiceRequest", "NavigateUp")
        return false
    }
}