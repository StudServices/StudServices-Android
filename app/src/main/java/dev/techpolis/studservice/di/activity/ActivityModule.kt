package dev.techpolis.studservice.di.activity

import android.view.LayoutInflater
import androidx.fragment.app.FragmentManager
import dagger.Module
import dagger.Provides
import dev.techpolis.studservice.common.base.BaseActivity
import dev.techpolis.studservice.common.nav.BackPressDispatcher

@Module
object ActivityModule {
    @Provides @ActivityScope fun layoutInflater(baseActivity: BaseActivity): LayoutInflater =
        LayoutInflater.from(baseActivity)!!

    @Provides @ActivityScope fun fragmentManager(baseActivity: BaseActivity): FragmentManager =
        baseActivity.supportFragmentManager

    @Module interface Binds {
        @dagger.Binds fun backPressDispatcher(baseActivity: BaseActivity): BackPressDispatcher
    }

}