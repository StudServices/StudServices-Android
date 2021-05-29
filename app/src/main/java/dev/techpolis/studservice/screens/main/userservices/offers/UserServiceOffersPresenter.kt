package dev.techpolis.studservice.screens.main.userservices.offers

import dev.techpolis.studservice.data.Status
import dev.techpolis.studservice.data.entities.ServiceEntity
import dev.techpolis.studservice.interactors.ServiceInteractor
import dev.techpolis.studservice.screens.common.mvp.MvpPresenter
import kotlinx.coroutines.*

class UserServiceOffersPresenter(
    private val serviceInteractor: ServiceInteractor,
): MvpPresenter<UserServiceOffersMvpView>, UserServiceOffersMvpView.Listener {

    private lateinit var view: UserServiceOffersMvpView
    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    override fun bindView(view: UserServiceOffersMvpView) {
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

    override fun onServiceOfferClicked(service: ServiceEntity) {
//        TODO("Not yet implemented")
    }

    override fun onBackPressed(): Boolean {
//        TODO("Not yet implemented")
        return false
    }
}