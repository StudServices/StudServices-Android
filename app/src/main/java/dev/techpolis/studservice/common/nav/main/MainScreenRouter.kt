package dev.techpolis.studservice.common.nav.main

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
}