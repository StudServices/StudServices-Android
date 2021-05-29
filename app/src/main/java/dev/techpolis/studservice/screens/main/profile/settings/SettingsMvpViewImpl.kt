package dev.techpolis.studservice.screens.main.profile.settings

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatSpinner
import androidx.core.widget.doOnTextChanged
import dev.techpolis.studservice.R
import dev.techpolis.studservice.screens.common.mvp.MvpViewObservableBase

class SettingsMvpViewImpl(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?
) : MvpViewObservableBase<SettingsMvpView.Listener>(), SettingsMvpView {
    override var rootView: View =
        layoutInflater.inflate(R.layout.fragment_main__profile__settings, parent, false)
    private val spnGeography: AppCompatSpinner = findViewById(R.id.fragment_main__profile__settings__geography)
    private val btnChangePhoto: AppCompatButton = findViewById(R.id.fragment_main__profile__settings__change_avatar)
    private val evChangeDescription: AppCompatEditText = findViewById(R.id.fragment_main__profile__settings__description)

    init {
        spnGeography.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                listeners.forEach {
                    it.onGeographyChanged(parent?.getItemAtPosition(position).toString())
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        btnChangePhoto.setOnClickListener {
            listeners.forEach {
                it.onChangePhotoBtnClicked()
            }
        }

        evChangeDescription.doOnTextChanged{text, _, _, _ ->
            listeners.forEach {
                it.onDescriptionFieldTextChanged(text.toString())
            }

        }


    }

}