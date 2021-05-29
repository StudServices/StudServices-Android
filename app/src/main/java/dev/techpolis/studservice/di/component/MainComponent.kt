package dev.techpolis.studservice.di.component

import android.os.Bundle
import dagger.BindsInstance
import dagger.Subcomponent
import dev.techpolis.studservice.di.module.ActivityModule
import dev.techpolis.studservice.di.scope.MainScope
import dev.techpolis.studservice.di.module.MainModule
import dev.techpolis.studservice.di.qual.MainBundle
import dev.techpolis.studservice.screens.main.MainFragment

@Subcomponent(modules = [
    MainModule::class,
    MainModule.Binds::class
])
@MainScope
interface MainComponent {

    @Subcomponent.Builder
    interface Builder {

        @BindsInstance
        fun mainFragment(mainFragment: MainFragment): Builder

        @BindsInstance
        fun savedInstanceState(@MainBundle bundle: Bundle?): Builder

        fun build(): MainComponent
    }

    fun inject(mainFragment: MainFragment)
}