package dev.techpolis.studservice.common.mvp.factory

import android.view.LayoutInflater
import javax.inject.Inject

class MvpViewFactory @Inject constructor(
    private val layoutInflater: LayoutInflater,
)