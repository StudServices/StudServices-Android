package dev.techpolis.studservice.screens.main.search

import android.util.Log
import dev.techpolis.studservice.data.Status
import dev.techpolis.studservice.data.entities.ServiceEntity
import dev.techpolis.studservice.screens.common.mvp.MvpPresenter
import dev.techpolis.studservice.screens.common.nav.BackPressDispatcher
import dev.techpolis.studservice.screens.common.nav.main.MainScreenRouter
import dev.techpolis.studservice.data.model.ServiceTypeEnum
import dev.techpolis.studservice.interactors.ServiceInteractor
import dev.techpolis.studservice.providers.FiltersProvider
import dev.techpolis.studservice.providers.ServiceInfoProvider
import dev.techpolis.studservice.screens.main.search.filters.FiltersMvpView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.util.*

class SearchPresenter(
    private val serviceInteractor: ServiceInteractor,
    private val serviceInfoProvider: ServiceInfoProvider,
    private val filtersProvider: FiltersProvider,
    private val mainScreenRouter: MainScreenRouter,
    private val backPressDispatcher: BackPressDispatcher,
) : MvpPresenter<SearchMvpView>, SearchMvpView.Listener, FiltersMvpView.Listener {

    private lateinit var view: SearchMvpView
    private lateinit var filtersMvpView: FiltersMvpView

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    override fun bindView(view: SearchMvpView) {
        this.view = view
        this.filtersMvpView = view.getFiltersMvpView()
        filtersProvider.apply {
            filtersMvpView.setTagList(tags)
            filtersMvpView.setTypeTab(type)
        }
        initFilteredData()
    }

    override fun onStart() {
        view.registerListener(this)
        filtersMvpView.registerListener(this)
        backPressDispatcher.registerListener(this)
        filtersMvpView.setContentVisible(filtersProvider.isContentVisible)
        view.setClearIconVisibility(filtersProvider.title.isNotEmpty())
    }

    override fun onStop() {
        view.unregisterListener(this)
        filtersMvpView.unregisterListener(this)
        backPressDispatcher.unregisterListener(this)
    }

    override fun onDestroy() {
        filtersProvider.clear()
    }

    override fun onSearchFieldTextChanged(text: String) {
        if (text.isNotEmpty()) {
            view.setClearIconVisibility(true)
        } else {
            view.setClearIconVisibility(false)
        }
        filtersProvider.title = text
        initFilteredData()
    }

    override fun onBackIconClicked() {
        mainScreenRouter.navigateUp()
    }

    override fun onClearIconClicked() {
        view.clearSearchFieldText()
        filtersProvider.title = ""
        initFilteredData()
    }

    override fun onApplyButtonClicked(priceFrom: Int, priceTo: Int) {
        filtersProvider.priceFrom = priceFrom
        filtersProvider.priceTo = priceTo
        initFilteredData()
    }

    override fun onArrowClicked(isContentVisible: Boolean) {
        filtersProvider.isContentVisible = !isContentVisible
        filtersMvpView.setContentVisible(filtersProvider.isContentVisible)
    }

    override fun onServiceClicked(service: ServiceEntity) {
        serviceInfoProvider.service = service
        mainScreenRouter.toServiceInfo()
    }

    override fun onChipDeleted(tagText: String) {
        filtersProvider.tags.remove(tagText)
    }

    override fun onChipAdded(tagText: String) {
        filtersProvider.tags.add(tagText)
    }

    override fun onBackPressed(): Boolean {
        Log.e("SearchPresenter", "NavigateUp")
        mainScreenRouter.navigateUp()
        return true
    }

    override fun onTabSelected(serviceTypeEnum: ServiceTypeEnum?) {
        filtersProvider.type = serviceTypeEnum
    }

    private fun initFilteredData() {
        val services = mutableListOf<ServiceEntity>()

        lateinit var requestStatus: Status
        coroutineScope.launch {
            if (filtersProvider.type == null) {
                val servicesOffers = serviceInteractor.getServices(ServiceTypeEnum.OFFER)
                requestStatus = servicesOffers.status
                val servicesRequests = serviceInteractor.getServices(ServiceTypeEnum.REQUEST)
                if (servicesRequests.status == Status.Error) {
                    requestStatus = Status.Error
                }
                if (servicesOffers.data != null)
                    services.addAll(servicesOffers.data)
                if (servicesRequests.data != null)
                    services.addAll(servicesRequests.data)
            } else {
                val newServices = serviceInteractor.getServices(type = filtersProvider.type!!)
                requestStatus = newServices.status
                newServices.data?.let { services.addAll(it) }
            }

            if (requestStatus is Status.Success) {
                view.bindData(
                    services.filter { se -> filterPredicate(se) }
                )

            }
        }
    }

    private fun filterPredicate(se: ServiceEntity): Boolean {
        return se.tagList.map { it.toLowerCase(Locale.ROOT) }
            .containsAll(filtersProvider.tags.map { it.toLowerCase(Locale.ROOT) })
                && se.price in filtersProvider.priceFrom..filtersProvider.priceTo
                && (filtersProvider.type == null || filtersProvider.type == se.type)
                && (filtersProvider.title.isEmpty() || se.title.contains(
            filtersProvider.title,
            ignoreCase = true
        ))
    }
}
