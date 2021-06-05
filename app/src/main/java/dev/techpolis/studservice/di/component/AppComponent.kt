package dev.techpolis.studservice.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dev.techpolis.studservice.di.module.AppModule
import dev.techpolis.studservice.di.module.GoogleAuthModule
import dev.techpolis.studservice.di.module.NetworkModule
import dev.techpolis.studservice.di.module.RoomModule
import javax.inject.Singleton

@Component(
    modules = [
        AppModule::class,
        AppModule.Binds::class,
        RoomModule::class,
        NetworkModule::class,
        GoogleAuthModule::class
    ]
)
@Singleton
interface AppComponent {

    fun newActivityComponentBuilder(): ActivityComponent.Builder

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}