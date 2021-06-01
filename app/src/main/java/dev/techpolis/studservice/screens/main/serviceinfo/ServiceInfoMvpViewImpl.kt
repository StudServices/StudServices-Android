package dev.techpolis.studservice.screens.main.serviceinfo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatTextView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import dev.techpolis.studservice.R
import dev.techpolis.studservice.data.entities.ServiceEntity
import dev.techpolis.studservice.data.model.ServiceTypeEnum
import dev.techpolis.studservice.screens.common.mvp.MvpViewObservableBase
import dev.techpolis.studservice.utils.timeToString

class ServiceInfoMvpViewImpl(
    private val layoutInflater: LayoutInflater,
    parent: ViewGroup?
) : MvpViewObservableBase<ServiceInfoMvpView.Listener>(), ServiceInfoMvpView {

    override var rootView: View =
        layoutInflater.inflate(R.layout.fragment_main__service_info, parent, false)

    private val tvTitle: AppCompatTextView = findViewById(R.id.fragment_main__service_info__title)
    private val tvDeadline: AppCompatTextView =
        findViewById(R.id.fragment_main__service_info__deadline)
    private val tvOwner: AppCompatTextView =
        findViewById(R.id.fragment_main__service_info__owner_name)
    private val tvDescription: AppCompatTextView =
        findViewById(R.id.fragment_main__service_info__description)
    private val tvType: AppCompatTextView =
        findViewById(R.id.fragment_main__service_info__service_type)
    private val tvPrice: AppCompatTextView = findViewById(R.id.fragment_main__service_info__price)
    private val tvTags: ChipGroup = findViewById(R.id.fragment_main__service_info__cgTags)
    private val btnContact: AppCompatButton =
        findViewById(R.id.fragment_main__service_info__contact_btn)
    private val btnBack: AppCompatImageButton =
        findViewById(R.id.fragment_main__service_info__back_btn)

    private lateinit var serviceEntity: ServiceEntity

    init {
        btnBack.setOnClickListener { listeners.forEach { it.onBackArrowBtnClicked() } }
        btnContact.setOnClickListener { listeners.forEach { it.onContactBtnClicked() } }
    }

    override fun bindData(service: ServiceEntity) {
        serviceEntity = service
        tvTitle.text = service.title
        tvOwner.text = "Owner with id=${service.ownerId}"
        tvDescription.text = service.description
        tvType.text = when (service.type) {
            ServiceTypeEnum.OFFER -> "Offer"
            ServiceTypeEnum.REQUEST -> "Request"
        }
        tvPrice.text = "${service.price} ₽"
        tvDeadline.text = "${timeToString(service.deadlineTime)}"

        service.tagList.forEach { tagText ->
            val newChip = layoutInflater.inflate(R.layout.custom_chip, tvTags, false) as Chip
            newChip.text = tagText
            tvTags.addView(newChip)
        }
    }

}