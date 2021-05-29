package dev.techpolis.studservice.di.component

import android.os.Bundle
import dagger.BindsInstance
import dagger.Subcomponent
import dev.techpolis.studservice.di.scope.ActivityScope
import dev.techpolis.studservice.di.module.ActivityModule
import dev.techpolis.studservice.di.qual.ActivityBundle
import dev.techpolis.studservice.screens.common.base.BaseActivity
import dev.techpolis.studservice.screens.common.base.BaseFragment

@Subcomponent(
    modules = [
        ActivityModule::class,
        ActivityModule.Binds::class
    ]
)
@ActivityScope
interface ActivityComponent {

    fun newMainComponentBuilder(): MainComponent.Builder

    @Subcomponent.Builder
    interface Builder {

        @BindsInstance
        fun activity(baseActivity: BaseActivity): Builder

        @BindsInstance
        fun savedInstanceState(@ActivityBundle bundle: Bundle?): Builder

        fun build(): ActivityComponent
    }

    fun inject(baseActivity: BaseActivity)

    fun inject(baseFragment: BaseFragment)
}
