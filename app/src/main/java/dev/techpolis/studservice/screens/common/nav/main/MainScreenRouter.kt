package dev.techpolis.studservice.screens.common.nav.main

import android.os.Bundle
import com.ncapdevi.fragnav.FragNavController
import com.ncapdevi.fragnav.FragNavTransactionOptions
import dev.techpolis.studservice.screens.common.mvp.MvpView
import dev.techpolis.studservice.screens.main.MainMvpView

interface MainScreenRouter : FragNavController.RootFragmentListener {
    fun toProfile()
    fun toProfileSettings()
    fun toServiceInfo()
    fun toServices()
    fun toUserServices()
    fun toSearchScreen()
    fun toNewService()
    fun navigateUp()
    fun onSaveInstanceState(outState: Bundle?)

    fun bindBottomBarView(view: MainMvpView)
    fun toDatePicker()
}