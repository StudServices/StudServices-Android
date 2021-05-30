package dev.techpolis.studservice.screens.main.services_item

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import com.bumptech.glide.RequestManager
import dev.techpolis.studservice.R
import dev.techpolis.studservice.data.entities.ServiceEntity
import dev.techpolis.studservice.screens.common.mvp.MvpViewObservableBase
import dev.techpolis.studservice.utils.timeToString
import java.text.SimpleDateFormat
import java.util.*

class ServicesItemMvpViewImpl(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?,
    private val listener: ServicesItemMvpView.Listener,
    private val glide: RequestManager
) : MvpViewObservableBase<ServicesItemMvpView.Listener>(), ServicesItemMvpView {

    override var rootView: View =
        layoutInflater.inflate(R.layout.fragment_main__services__snippet, parent, false)
    private val tvTitle: AppCompatTextView =
        findViewById(R.id.fragment_main__services__offers__snippet__title)
    private val tvPrice: AppCompatTextView =
        findViewById(R.id.fragment_main__services__offers__snippet__price)
    private val tvDescription: AppCompatTextView =
        findViewById(R.id.fragment_main__services__offers__snippet__desc)
    private val tvDeadline: AppCompatTextView =
        findViewById(R.id.fragment_main__services__offers__snippet__deadline)

    private lateinit var service: ServiceEntity

    init {
        rootView.setOnClickListener { listener.onServiceClicked(service) }
    }

    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    override fun bindData(service: ServiceEntity) {
        this.service = service
        tvTitle.text = service.title
        tvPrice.text = "${service.price} ₽"
        tvDescription.text = service.description
        tvDeadline.text = "до ${timeToString(service.deadlineTime)}"
    }
}