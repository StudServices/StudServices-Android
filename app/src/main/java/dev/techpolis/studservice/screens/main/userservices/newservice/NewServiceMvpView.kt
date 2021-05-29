package dev.techpolis.studservice.screens.main.userservices.newservice

import android.widget.RadioGroup
import com.google.android.material.chip.ChipGroup
import dev.techpolis.studservice.screens.common.mvp.MvpViewObservable
import dev.techpolis.studservice.screens.common.nav.BackPressedListener

interface NewServiceMvpView: MvpViewObservable<NewServiceMvpView.Listener> {
    interface Listener : BackPressedListener {
        fun onCreateServiceBtnClicked()
        fun onTitleFieldTextChanged(title: String)
        fun onPasswordFieldTextChanged(password: String)
        fun onPriceFieldTextChanged(price: String)
        fun onTagsCheckedChanged(chipGroup: ChipGroup, position: Int)
        fun onServiceTypeChanged(radioGroup: RadioGroup)
        fun onGeographyChanged(geography: String)
    }

}