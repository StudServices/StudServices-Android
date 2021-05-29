package dev.techpolis.studservice

import android.app.Application
import dev.techpolis.studservice.di.component.AppComponent
import dev.techpolis.studservice.di.component.DaggerAppComponent

class StudServicesApp: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .application(this)
            .build()
    }

}