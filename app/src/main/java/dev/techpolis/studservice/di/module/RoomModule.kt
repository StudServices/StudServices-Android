package dev.techpolis.studservice.di.module

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dev.techpolis.studservice.data.database.AppDatabase
import dev.techpolis.studservice.data.database.ServiceDAO
import javax.inject.Singleton

@Module
object RoomModule {
    private const val DATABASE_NAME = "stud_database_three"

    @Singleton
    @Provides
    fun provideDatabase(application: Application): AppDatabase =
        Room.databaseBuilder(
            application.applicationContext,
            AppDatabase::class.java,
            DATABASE_NAME
        ).build()

    @Singleton
    @Provides
    fun provideServiceDAO(database: AppDatabase): ServiceDAO = database.serviceDAO()
}