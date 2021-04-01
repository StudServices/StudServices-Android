package dev.techpolis.studservice.di

import com.google.gson.Gson
import dagger.Provides
import javax.inject.Singleton

class GsonModule {

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return Gson()
    }

}