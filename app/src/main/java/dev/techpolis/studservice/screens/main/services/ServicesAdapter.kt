package dev.techpolis.studservice.screens.main.services

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import dev.techpolis.studservice.data.entities.ServiceEntity
import dev.techpolis.studservice.screens.common.mvp.factory.MvpViewFactory
import dev.techpolis.studservice.screens.main.services_item.ServicesItemMvpView

class ServicesAdapter(
    private val listener: ServicesItemMvpView.Listener,
    private val mvpViewFactory: MvpViewFactory,
    private val glide: RequestManager,
): RecyclerView.Adapter<ServicesAdapter.ServicesViewHolder>(), ServicesItemMvpView.Listener {
    class ServicesViewHolder(val servicesItemMvpView: ServicesItemMvpView) :
            RecyclerView.ViewHolder(servicesItemMvpView.rootView)

    val services: MutableList<ServiceEntity> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServicesViewHolder {
        val view = mvpViewFactory.createServiceItemMvpView(parent, this, glide)
        return ServicesViewHolder(view)
    }

    override fun onBindViewHolder(holder: ServicesViewHolder, position: Int) {
        val currService = services[position]
        holder.servicesItemMvpView.bindData(currService)
    }

    override fun getItemCount(): Int = services.size

    override fun onServiceClicked(service: ServiceEntity) {
        listener.onServiceClicked(service)
    }

    fun bindData(serviceList: List<ServiceEntity>) {
        services.clear()
        services.addAll(serviceList)
        notifyDataSetChanged()
    }
}