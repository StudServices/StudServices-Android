package dev.techpolis.studservice.screens.main.services.requests

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

class ServiceRequestsMvpViewImpl(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?,
    viewFactory: MvpViewFactory,
) : MvpViewObservableBase<ServiceRequestsMvpView.Listener>(), ServiceRequestsMvpView,
    ServicesItemMvpView.Listener {

    override var rootView: View =
        layoutInflater.inflate(R.layout.fragment_main__services__requests, parent, false)
    private val recyclerView: RecyclerView =
        findViewById(R.id.fragment_main__services__requests__recycler)
    private val servicesAdapter: ServicesAdapter =
        viewFactory.createServicesAdapter(this, Glide.with(context))

    init {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = servicesAdapter
//            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
        }
    }

    override fun bindData(listRequests: List<ServiceEntity>) {
        servicesAdapter.bindData(listRequests)
    }

    override fun onServiceClicked(service: ServiceEntity) {
        listeners.forEach { it.onServiceClicked(service) }
    }
}