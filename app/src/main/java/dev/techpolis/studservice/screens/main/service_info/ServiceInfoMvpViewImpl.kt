package dev.techpolis.studservice.screens.main.service_info

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.isVisible
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
    private val tvDescriptionTitle: AppCompatTextView =
        findViewById(R.id.fragment_main__service_info__description_title)
    private val tvType: AppCompatTextView =
        findViewById(R.id.fragment_main__service_info__service_type)
    private val tvPrice: AppCompatTextView = findViewById(R.id.fragment_main__service_info__price)
    private val cgTags: ChipGroup = findViewById(R.id.fragment_main__service_info__cgTags)
    private val tvTagsTitle: AppCompatTextView =
        findViewById(R.id.fragment_main__service_info__cgTags_title)
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
        if (service.description.isEmpty()) {
            tvDescription.isVisible = false
            tvDescriptionTitle.isVisible = false
        } else {
            tvDescription.text = service.description
        }
        tvType.text = when (service.type) {
            ServiceTypeEnum.OFFER -> "Offer"
            ServiceTypeEnum.REQUEST -> "Request"
        }
        tvPrice.text = "${service.price} ₽"
        tvDeadline.text = timeToString(service.deadlineTime)

        if (service.tagList.isEmpty()) {
            tvTagsTitle.isVisible = false
            cgTags.isVisible = false
        } else {
            service.tagList.forEach { tagText ->
                val newChip = layoutInflater.inflate(R.layout.custom_chip, cgTags, false) as Chip
                newChip.text = tagText
                cgTags.addView(newChip)
            }
        }
    }

}