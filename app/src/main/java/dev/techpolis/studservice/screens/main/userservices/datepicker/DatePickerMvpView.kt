package dev.techpolis.studservice.screens.main.userservices.datepicker

import dev.techpolis.studservice.screens.common.mvp.MvpViewObservable
import dev.techpolis.studservice.screens.common.nav.BackPressedListener

interface DatePickerMvpView: MvpViewObservable<DatePickerMvpView.Listener> {
    interface Listener : BackPressedListener {
        fun onOkBtnClicked(dayOfMonth: Int, month: Int, year: Int)
    }

}