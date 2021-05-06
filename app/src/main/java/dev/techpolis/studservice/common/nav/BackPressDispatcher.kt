package dev.techpolis.studservice.common.nav

interface BackPressDispatcher {
    fun registerListener(listener: BackPressedListener)
    fun unregisterListener(listener: BackPressedListener)
}