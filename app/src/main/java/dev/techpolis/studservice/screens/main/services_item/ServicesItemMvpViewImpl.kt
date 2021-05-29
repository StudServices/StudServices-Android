package dev.techpolis.studservice.screens.main.services_item

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.RequestManager
import dev.techpolis.studservice.R
import dev.techpolis.studservice.data.entities.ServiceEntity
import dev.techpolis.studservice.screens.common.mvp.MvpViewObservableBase

class ServicesItemMvpViewImpl(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?,
    private val listener: ServicesItemMvpView.Listener,
    private val glide: RequestManager
) : MvpViewObservableBase<ServicesItemMvpView.Listener>(), ServicesItemMvpView {

    override var rootView: View =
        layoutInflater.inflate(R.layout.fragment_main__services__snippet, parent, false)
    private val ivLogo: ImageView =
        findViewById(R.id.fragment_main__services__offers__snippet__logo)
    private val tvTitle: TextView =
        findViewById(R.id.fragment_main__services__offers__snippet__title)
    private val tvPrice: TextView =
        findViewById(R.id.fragment_main__services__offers__snippet__price)
    private val tvDescription: TextView =
        findViewById(R.id.fragment_main__services__offers__snippet__desc)

    private lateinit var service: ServiceEntity

    init {
        rootView.setOnClickListener { listener.onServiceClicked(service) }
    }

    override fun bindData(service: ServiceEntity) {
        this.service = service
        glide.load(
            getDrawable(if (service.avatarUrl == "av1") R.drawable.av1 else R.drawable.av2)
        ).into(
            ivLogo
        )
        tvTitle.text = service.title
        tvPrice.text = "$${service.price}"
        tvDescription.text = service.description
    }
}