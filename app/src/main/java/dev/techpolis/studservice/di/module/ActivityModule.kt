package dev.techpolis.studservice.di.module

import android.view.LayoutInflater
import androidx.fragment.app.FragmentManager
import dagger.Module
import dagger.Provides
import dev.techpolis.studservice.di.qual.ActivityFragmentManager
import dev.techpolis.studservice.di.scope.ActivityScope
import dev.techpolis.studservice.screens.common.base.BaseActivity
import dev.techpolis.studservice.screens.common.nav.BackPressDispatcher
import dev.techpolis.studservice.screens.common.nav.app.AppScreenRouter
import dev.techpolis.studservice.screens.common.nav.app.AppScreenRouterImpl

@Module
object ActivityModule {

    @Provides
    @ActivityScope
    fun layoutInflater(baseActivity: BaseActivity): LayoutInflater =
        LayoutInflater.from(baseActivity)!!

    @Provides
    @ActivityFragmentManager
    @ActivityScope
    fun fragmentManager(baseActivity: BaseActivity): FragmentManager =
        baseActivity.supportFragmentManager

    @Module
    interface Binds {
        @dagger.Binds
        fun backPressDispatcher(baseActivity: BaseActivity): BackPressDispatcher

        @dagger.Binds
        fun appScreenRouter(appScreenRouterImpl: AppScreenRouterImpl): AppScreenRouter
    }

}