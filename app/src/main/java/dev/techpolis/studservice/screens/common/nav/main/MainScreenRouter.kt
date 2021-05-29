package dev.techpolis.studservice.screens.common.nav.main

import android.os.Bundle
import com.ncapdevi.fragnav.FragNavController

interface MainScreenRouter : FragNavController.RootFragmentListener {
    fun toProfile()
    fun toProfileSettings()
    fun toServiceInfo()
    fun toServices()
    fun toUserServices()
    fun toMap()
    fun toFilters()
    fun toNewService()
    fun navigateUp()
    fun onSaveInstanceState(outState: Bundle?)
}