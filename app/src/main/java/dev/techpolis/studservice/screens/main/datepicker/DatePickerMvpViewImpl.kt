package dev.techpolis.studservice.screens.main.datepicker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageButton
import androidx.fragment.app.Fragment
import dev.techpolis.studservice.R
import dev.techpolis.studservice.screens.common.mvp.MvpViewObservableBase
import java.util.*

class DatePickerMvpViewImpl(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?,
    fragment: Fragment,
) : MvpViewObservableBase<DatePickerMvpView.Listener>(), DatePickerMvpView {

    override var rootView: View =
        layoutInflater.inflate(R.layout.fragment_main__user_services__date_picker, parent, false)

    private val picker: DatePicker = findViewById(R.id.fragment_main_user_services__date_picker_dp)
    private val okBtn: AppCompatButton = findViewById(R.id.fragment_main_user_services__date_picker__ok_btn)
    private val backBtn: AppCompatImageButton = findViewById(R.id.fragment_main_user_services__date_picker__back_btn)

    init {
        picker.minDate = Calendar.getInstance().timeInMillis

        okBtn.setOnClickListener {
            listeners.forEach {
                it.onOkBtnClicked(
                    dayOfMonth = picker.dayOfMonth,
                    month = picker.month,
                    year = picker.year)
            }

        }

        backBtn.setOnClickListener {
            listeners.forEach {
                it.onBackPressed()
            }
        }
    }

}