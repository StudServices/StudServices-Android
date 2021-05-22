package dev.techpolis.studservice.di.app

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dev.techpolis.studservice.di.activity.ActivityComponent
import javax.inject.Singleton

@Component
@Singleton interface AppComponent {
    fun newActivityComponentBuilder() : ActivityComponent.Builder

    @Component.Builder interface Builder {
        @BindsInstance fun application(application: Application): Builder
        fun build(): AppComponent
    }

}