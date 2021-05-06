package dev.techpolis.studservice.common.nav.app

import com.ncapdevi.fragnav.FragNavController

interface AppScreenRouter : FragNavController.RootFragmentListener {
    fun toSignIn()
    fun toSignUp()
    fun toMain()
}