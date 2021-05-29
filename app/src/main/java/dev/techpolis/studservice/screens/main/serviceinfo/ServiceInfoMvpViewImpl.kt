package dev.techpolis.studservice.screens.main.serviceinfo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import dev.techpolis.studservice.R
import dev.techpolis.studservice.data.entities.ServiceEntity
import dev.techpolis.studservice.screens.common.mvp.MvpViewObservableBase

class ServiceInfoMvpViewImpl(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?
) : MvpViewObservableBase<ServiceInfoMvpView.Listener>(), ServiceInfoMvpView {

    override var rootView: View =
        layoutInflater.inflate(R.layout.fragment_main__service_info, parent, false)

    private val tvTitle: AppCompatTextView = findViewById(R.id.fragment_main__service_info__tvTitle)
    private val tvDescription: AppCompatTextView =
        findViewById(R.id.fragment_main__service_info__tvDescription)
    private val tvType: AppCompatTextView = findViewById(R.id.fragment_main__service_info__type)
    private val tvCurrency: AppCompatTextView =
        findViewById(R.id.fragment_main__service_info__spnCurrency)
    private val tvPrice: AppCompatTextView = findViewById(R.id.fragment_main__service_info__etPrice)
    private val tvGeography: AppCompatTextView =
        findViewById(R.id.fragment_main__service_info__spnGeography)
    private val tvTags: AppCompatTextView = findViewById(R.id.fragment_main__service_info__etTags)

    private lateinit var serviceEntity: ServiceEntity

    override fun bindData(service: ServiceEntity) {
        serviceEntity = service
        tvTitle.text = service.title
        tvDescription.text = service.description
        tvType.text = "some type[not implementented]" //TODO()
        tvCurrency.text = "some currency[not implementented]" //TODO()
        tvPrice.text = "$${service.price}"
        tvGeography.text = "some geo[not implementented]" //TODO()
        tvTags.text = service.tagList.joinToString(separator = ", ") //TODO(CHIPS)
    }

}