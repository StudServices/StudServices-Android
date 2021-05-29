package dev.techpolis.studservice.screens.main.filters

import android.widget.RadioGroup
import com.google.android.material.chip.ChipGroup
import dev.techpolis.studservice.screens.common.mvp.MvpViewObservable
import dev.techpolis.studservice.screens.common.nav.BackPressedListener

interface FiltersMvpView: MvpViewObservable<FiltersMvpView.Listener> {
    interface Listener : BackPressedListener {
        fun onTagsCheckedChanged(chipGroup: ChipGroup, position: Int)

        fun onServiceTypeChanged(radioGroup: RadioGroup)

        fun onGeographyChanged(geography: String)
        fun onFilterBtnClicked()
    }

}