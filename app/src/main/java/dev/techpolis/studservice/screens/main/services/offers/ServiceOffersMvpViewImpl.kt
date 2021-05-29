package dev.techpolis.studservice.screens.main.services.offers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

    init {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = servicesAdapter
//            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
        }
    }

    override fun onServiceClicked(service: ServiceEntity) {
        listeners.forEach { it.onServiceOfferClicked(service) }
    }

    override fun bindData(listOffers: List<ServiceEntity>) {
        servicesAdapter.bindData(listOffers)
    }

}