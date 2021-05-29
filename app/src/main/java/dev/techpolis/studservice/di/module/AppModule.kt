package dev.techpolis.studservice.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dev.techpolis.studservice.repositories.service.ServicesRepo
import dev.techpolis.studservice.repositories.service.ServicesRepoImpl
import dev.techpolis.studservice.repositories.user.UserRepo
import dev.techpolis.studservice.repositories.user.UserRepoImpl
import dev.techpolis.studservice.utils.Constants.Companion.PREFERENCE_NAME
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
object AppModule {

    @Singleton
    @Provides
    fun ioDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Singleton
    @Provides
    fun provideSharedPreferences(application: Application): SharedPreferences =
        application.getSharedPreferences(
            PREFERENCE_NAME,
            Context.MODE_PRIVATE
        )

    @Module
    interface Binds {
        @dagger.Binds
        fun userRepo(userRepoImpl: UserRepoImpl): UserRepo

        @dagger.Binds
        fun servicesRepo(servicesRepoImpl: ServicesRepoImpl): ServicesRepo
    }

}