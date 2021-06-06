package dev.techpolis.studservice.screens.main.user_services.new

import android.util.Log
import dev.techpolis.studservice.data.model.ServiceTypeEnum
import dev.techpolis.studservice.interactors.ServiceInteractor
import dev.techpolis.studservice.providers.NewServiceProvider
import dev.techpolis.studservice.screens.common.mvp.MvpPresenter
import dev.techpolis.studservice.screens.common.nav.BackPressDispatcher
import dev.techpolis.studservice.screens.common.nav.main.MainScreenRouter
import kotlinx.coroutines.*

class NewServicePresenter(
    private val mainScreenRouter: MainScreenRouter,
    private val backPressDispatcher: BackPressDispatcher,
    private val newServiceProvider: NewServiceProvider,
    private val serviceInteractor: ServiceInteractor,
) : MvpPresenter<NewServiceMvpView>, NewServiceMvpView.Listener {

    private lateinit var view: NewServiceMvpView
    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    override fun bindView(view: NewServiceMvpView) {
        this.view = view
        initData()
    }

    override fun onStart() {
        view.registerListener(this)
        backPressDispatcher.registerListener(this)
    }

    private fun initData() {
        newServiceProvider.apply {
            deadline?.let { view.setDate(it) }
            view.setTagList(tags)
            view.setTypeTab(type)
        }
    }

    override fun onStop() {
        view.unregisterListener(this)
        backPressDispatcher.unregisterListener(this)
    }

    override fun onDestroy() {
        Log.e("NewServicePresenter", "OnDestroy")
        coroutineScope.coroutineContext.cancelChildren()
    }

    override fun onCreateServiceBtnClicked(
        title: String,
        description: String,
//        serviceType: ServiceTypeEnum,
        price: Int,
//        deadline: Long,
//        tags: List<String>
    ) {
        coroutineScope.launch {
            serviceInteractor.addUserService(
                title,
                "2",
                description,
                price,
                newServiceProvider.tags,
                newServiceProvider.type,
                newServiceProvider.deadline?.toTimeMillis() ?: 0
            )
            newServiceProvider.clear()
        }
        mainScreenRouter.navigateUp()
    }

    override fun onBackBtnClicked() {
        onBackPressed()
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

    override fun onTypeSelected(type: ServiceTypeEnum) {
        newServiceProvider.type = type
    }

    override fun onBackPressed(): Boolean {
        newServiceProvider.clear()
        mainScreenRouter.navigateUp()
        return true
    }
}

