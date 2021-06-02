package dev.techpolis.studservice.screens.main.userservices.newservice

import android.util.Log
import dev.techpolis.studservice.data.model.DeadlineDate
import dev.techpolis.studservice.data.model.ServiceTypeEnum
import dev.techpolis.studservice.providers.NewServiceProvider
import dev.techpolis.studservice.screens.common.mvp.MvpPresenter
import dev.techpolis.studservice.screens.common.mvp.MvpView
import dev.techpolis.studservice.screens.common.nav.BackPressDispatcher
import dev.techpolis.studservice.screens.common.nav.main.MainScreenRouter

class NewServicePresenter(
    private val mainScreenRouter: MainScreenRouter,
    private val backPressDispatcher: BackPressDispatcher,
    private val newServiceProvider: NewServiceProvider,
): MvpPresenter<NewServiceMvpView>, NewServiceMvpView.Listener {

    private lateinit var view: NewServiceMvpView

    override fun bindView(view: NewServiceMvpView) {
        this.view = view
    }

    override fun onStart() {
        view.registerListener(this)
        backPressDispatcher.registerListener(this)
        newServiceProvider.deadline?.let { view.setDate(it) }
        newServiceProvider.tags.let {view.setTagList(it)}
    }

    override fun onStop() {
        view.unregisterListener(this)
        backPressDispatcher.unregisterListener(this)
    }

    override fun onDestroy() {
        Log.e("NewServicePresenter", "OnDestroy")
    }

    override fun onCreateServiceBtnClicked(
        title: String,
        desc: String,
        serviceType: ServiceTypeEnum,
        price: Double,
        deadline: String,
        tags: List<String>
    ) {
        mainScreenRouter.navigateUp()
    }

    override fun onBackBtnClicked() {
        newServiceProvider.clear()
        mainScreenRouter.navigateUp()
    }

    override fun onChipAdded(name: String) {
        newServiceProvider.tags.add(name)
    }

    override fun onChipDeleted(name: String) {
        newServiceProvider.tags.remove(name)
    }

    override fun getToDatePicker() {
        mainScreenRouter.toDatePicker()
    }

    override fun onBackPressed(): Boolean {
        newServiceProvider.clear()
        mainScreenRouter.navigateUp()
        return true
    }
}

