package dev.techpolis.studservice

import android.app.Application
import dev.techpolis.studservice.di.app.AppComponent
import dev.techpolis.studservice.di.app.DaggerAppComponent

class StudServicesApp: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
//        appComponent = DaggerAppComponent.builder()
//            .application(this)
//            .build()
    }

}