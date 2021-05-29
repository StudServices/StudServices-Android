package dev.techpolis.studservice.screens.main.userservices.requests

import dev.techpolis.studservice.data.Status
import dev.techpolis.studservice.data.entities.ServiceEntity
import dev.techpolis.studservice.interactors.ServiceInteractor
import dev.techpolis.studservice.screens.common.mvp.MvpPresenter
import kotlinx.coroutines.*

class UserServiceRequestsPresenter(
    private val serviceInteractor: ServiceInteractor,
): MvpPresenter<UserServiceRequestsMvpView>, UserServiceRequestsMvpView.Listener {

    private lateinit var view: UserServiceRequestsMvpView
    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    override fun bindView(view: UserServiceRequestsMvpView) {
        this.view = view
        initData()
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
//        TODO("Not yet implemented")
    }

    override fun onStop() {
//        TODO("Not yet implemented")
    }

    override fun onDestroy() {
        coroutineScope.coroutineContext.cancelChildren()
    }


    override fun onServiceRequestClicked(service: ServiceEntity) {
//        TODO("Not yet implemented")
    }

    override fun onBackPressed(): Boolean {
//        TODO("Not yet implemented")
        return false
    }
}