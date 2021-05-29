package dev.techpolis.studservice.screens.common.nav.app

import android.os.Bundle
import com.ncapdevi.fragnav.FragNavController

interface AppScreenRouter : FragNavController.RootFragmentListener {
    fun toSignIn()
    fun toSignUp()
    fun toMain()
    fun navigateUp()
    fun onSaveInstanceState(outState: Bundle?)
}