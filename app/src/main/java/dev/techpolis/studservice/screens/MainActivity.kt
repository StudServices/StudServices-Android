package dev.techpolis.studservice.screens

import android.os.Bundle
import dev.techpolis.studservice.R
import dev.techpolis.studservice.screens.common.base.BaseActivity

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
