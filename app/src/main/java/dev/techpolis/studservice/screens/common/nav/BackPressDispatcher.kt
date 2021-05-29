package dev.techpolis.studservice.screens.common.nav

interface BackPressDispatcher {
    fun registerListener(listener: BackPressedListener)
    fun unregisterListener(listener: BackPressedListener)
}