package dev.techpolis.studservice.screens.main.userservices.newservice

import dev.techpolis.studservice.data.model.ServiceTypeEnum
import dev.techpolis.studservice.screens.common.mvp.MvpPresenter
import dev.techpolis.studservice.screens.common.nav.BackPressDispatcher
import dev.techpolis.studservice.screens.common.nav.main.MainScreenRouter

class NewServicePresenter(
    private val mainScreenRouter: MainScreenRouter,
    private val backPressDispatcher: BackPressDispatcher,
): MvpPresenter<NewServiceMvpView>, NewServiceMvpView.Listener {
    private lateinit var view: NewServiceMvpView

    override fun bindView(view: NewServiceMvpView) {
        this.view = view
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
        TODO("Not yet implemented")
    }

    override fun onCreateServiceBtnClicked(
        title: String,
        desc: String,
        serviceType: ServiceTypeEnum,
        currency: String,
        price: Double,
        geography: String,
        tags: List<String>
    ) {
        TODO("Not yet implemented")
    }

    override fun onBackPressed(): Boolean {
//        TODO("Not yet implemented")
        return false
    }
}

