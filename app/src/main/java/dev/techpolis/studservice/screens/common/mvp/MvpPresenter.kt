package dev.techpolis.studservice.screens.common.mvp

interface MvpPresenter<T : MvpView> {
    fun bindView(view: T)
    fun onStart()
    fun onStop()
    fun onDestroy()
}