package dev.techpolis.studservice.screens.main.userservices.newservice

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.RadioGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatSpinner
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.widget.doOnTextChanged
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import dev.techpolis.studservice.R
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
    private val spnGeography: AppCompatSpinner = findViewById(R.id.fragment_main__user_services__new_service__spnGeography)
    private val cgTags: ChipGroup = findViewById(R.id.fragment_main__user_services__new_service__cgTags)
    private val btnCreate: AppCompatButton = findViewById(R.id.fragment_main__user_services__new_service__btnCreate)


    init {
        etTitle.doOnTextChanged { text, _, _, _ ->
            listeners.forEach {
                it.onTitleFieldTextChanged(text.toString())
            }
        }

        etDescription.doOnTextChanged { text, _, _, _ ->
            listeners.forEach {
                it.onPasswordFieldTextChanged(text.toString())
            }
        }

        etPrice.doOnTextChanged { text, _, _, _ ->
            listeners.forEach {
                it.onPriceFieldTextChanged(text.toString())
            }
        }

        cgTags.setOnCheckedChangeListener { chipGroup: ChipGroup, position: Int ->
            listeners.forEach {
                it.onTagsCheckedChanged(chipGroup, position)
            }
        }

        rgType.setOnCheckedChangeListener { radioGroup: RadioGroup, _ ->
            listeners.forEach {
                it.onServiceTypeChanged(radioGroup)
            }
        }

        spnGeography.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                listeners.forEach {
                    it.onGeographyChanged(parent?.getItemAtPosition(position).toString())
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        btnCreate.setOnClickListener {
            listeners.forEach {
                it.onCreateServiceBtnClicked()
            }
        }
    }

}