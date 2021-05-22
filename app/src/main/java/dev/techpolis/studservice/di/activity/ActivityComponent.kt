package dev.techpolis.studservice.di.activity

import android.os.Bundle
import dagger.BindsInstance
import dagger.Subcomponent
import dev.techpolis.studservice.common.base.BaseActivity
import dev.techpolis.studservice.di.presentation.PresentationComponent


@Subcomponent(
    modules = [
        ActivityModule::class,
        ActivityModule.Binds::class
    ]
) @ActivityScope interface ActivityComponent {

    fun newPresentationComponent(): PresentationComponent

    @Subcomponent.Builder interface Builder {
        @BindsInstance fun activity(baseActivity: BaseActivity) : Builder
        @BindsInstance fun savedInstanceState(bundle: Bundle?): Builder
        fun build(): ActivityComponent
    }

    fun inject(baseActivity: BaseActivity)
}
