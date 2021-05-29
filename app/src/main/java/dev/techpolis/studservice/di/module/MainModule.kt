package dev.techpolis.studservice.di.module

import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.Module
import dagger.Provides
import dev.techpolis.studservice.R
import dev.techpolis.studservice.di.scope.ActivityScope
import dev.techpolis.studservice.di.qual.MainFragmentManager
import dev.techpolis.studservice.di.scope.MainScope
import dev.techpolis.studservice.screens.common.nav.main.MainScreenRouter
import dev.techpolis.studservice.screens.common.nav.main.MainScreenRouterImpl
import dev.techpolis.studservice.screens.main.MainFragment

@Module
object MainModule {

    @Provides
    @MainFragmentManager
    @MainScope
    fun fragmentManager(mainFragment: MainFragment): FragmentManager =
        mainFragment.childFragmentManager

    @Module
    interface Binds {
        @dagger.Binds
        fun mainScreenRouter(mainScreenRouterImpl: MainScreenRouterImpl): MainScreenRouter
    }

}