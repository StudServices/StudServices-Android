package dev.techpolis.studservice.screens.main.userservices.newservice

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatSpinner
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import dev.techpolis.studservice.R
import dev.techpolis.studservice.data.model.ServiceTypeEnum
import dev.techpolis.studservice.screens.common.mvp.MvpViewObservableBase

class NewServiceMvpViewImpl(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?
) : MvpViewObservableBase<NewServiceMvpView.Listener>(), NewServiceMvpView {
    override var rootView: View =
        layoutInflater.inflate(R.layout.fragment_main__user_services__new_service, parent, false)

    private val etTitle: AppCompatEditText = findViewById(R.id.fragment_main__user_services__new_service__etTitle)
    private val etDescription: AppCompatEditText = findViewById(R.id.fragment_main__user_services__new_service__etDescription)
    private val rgType: RadioGroup = findViewById(R.id.fragment_main__user_services__new_service__rg)
    private val etPrice: AppCompatEditText = findViewById(R.id.fragment_main__user_services__new_service__etPrice)
    private val spnCurrency: AppCompatSpinner = findViewById(R.id.fragment_main__user_services__new_service__spnCurrency)
    private val spnGeography: AppCompatSpinner = findViewById(R.id.fragment_main__user_services__new_service__spnGeography)
    private val cgTags: ChipGroup = findViewById(R.id.fragment_main__user_services__new_service__cgTags)
    private val btnCreate: AppCompatButton = findViewById(R.id.fragment_main__user_services__new_service__btnCreate)


    init {
        btnCreate.setOnClickListener {
            listeners.forEach {
                it.onCreateServiceBtnClicked(
                    title = etTitle.text.toString(),
                    desc = etDescription.text.toString(),
                    price = etPrice.text.toString().toDouble(),
                    serviceType = getServiceTypeEnum(),
                    currency = spnCurrency.selectedItem.toString(),
                    geography = spnGeography.selectedItem.toString(),
                    tags = getTagsList()
                )
            }


        }
    }

    private fun getTagsList(): List<String> {
        val checkedChipsText = mutableListOf<String>()
        cgTags.checkedChipIds.forEach {
            val chip = cgTags.findViewById<Chip>(it).text.toString()
            checkedChipsText.add(chip)
        }
        return checkedChipsText
    }

    private fun getServiceTypeEnum() =
        if (rgType.checkedRadioButtonId == 0)
            ServiceTypeEnum.OFFER
        else
            ServiceTypeEnum.REQUEST


}