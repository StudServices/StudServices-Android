package dev.techpolis.studservice.screens.main.userservices.datepicker

import android.text.style.TtsSpan
import android.util.Log
import dev.techpolis.studservice.data.model.DeadlineDate
import dev.techpolis.studservice.providers.NewServiceProvider
import dev.techpolis.studservice.screens.common.mvp.MvpPresenter
import dev.techpolis.studservice.screens.common.nav.BackPressDispatcher
import dev.techpolis.studservice.screens.common.nav.main.MainScreenRouter

class DatePickerPresenter(
    private val mainScreenRouter: MainScreenRouter,
    private val backPressDispatcher: BackPressDispatcher,
    private val newServiceProvider: NewServiceProvider,
): MvpPresenter<DatePickerMvpView>, DatePickerMvpView.Listener {
    private lateinit var view: DatePickerMvpView

    override fun bindView(view: DatePickerMvpView) {
        this.view = view
    }

    override fun onStart() {
        view.registerListener(this)
        backPressDispatcher.registerListener(this)
        Log.e("DatePickerPresenter", "onStart")
    }

    override fun onStop() {
        view.unregisterListener(this)
        backPressDispatcher.unregisterListener(this)
        Log.e("DatePickerPresenter", "onStop")
    }

    override fun onDestroy() {
//        TODO("Not yet implemented")
    }

    override fun onOkBtnClicked(dayOfMonth: Int, month: Int, year: Int) {
        newServiceProvider.deadline = DeadlineDate(dayOfMonth, month, year)
        onBackPressed()
    }

    override fun onBackPressed(): Boolean {
        mainScreenRouter.navigateUp()
        return true
    }
}