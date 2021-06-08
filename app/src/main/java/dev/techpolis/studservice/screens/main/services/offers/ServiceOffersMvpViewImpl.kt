package dev.techpolis.studservice.screens.main.services.offers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import dev.techpolis.studservice.R
import dev.techpolis.studservice.data.entities.ServiceEntity
import dev.techpolis.studservice.screens.common.mvp.MvpViewObservableBase
import dev.techpolis.studservice.screens.common.mvp.factory.MvpViewFactory
import dev.techpolis.studservice.screens.main.services.ServicesAdapter
import dev.techpolis.studservice.screens.main.services_item.ServicesItemMvpView

class ServiceOffersMvpViewImpl(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?,
    viewFactory: MvpViewFactory,
) : MvpViewObservableBase<ServiceOffersMvpView.Listener>(), ServiceOffersMvpView,
    ServicesItemMvpView.Listener {

    override var rootView: View =
        layoutInflater.inflate(R.layout.fragment_main__services__offers, parent, false)

    private val recyclerView: RecyclerView =
        findViewById(R.id.fragment_main__services__offers__recycler)
    private val servicesAdapter: ServicesAdapter =
        viewFactory.createServicesAdapter(this, Glide.with(context))
    private val refresh: SwipeRefreshLayout = findViewById(R.id.fragment_main__services__offers__refresh)
    private val tvNoResults: AppCompatTextView = findViewById(R.id.fragment_main__services__offers__not_found_tv)

    init {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = servicesAdapter
//            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
        }
        refresh.setOnRefreshListener { listeners.forEach { it.onPullToRefresh() } }
    }

    override fun onServiceClicked(service: ServiceEntity) {
        listeners.forEach { it.onServiceOfferClicked(service) }
    }

    override fun bindData(listOffers: List<ServiceEntity>) {
        tvNoResults.isVisible = listOffers.isEmpty()
        servicesAdapter.bindData(listOffers)
    }

    override fun setRefreshed() {
        refresh.isRefreshing = false
    }

}