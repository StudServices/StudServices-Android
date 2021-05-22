package dev.techpolis.studservice.di.app

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
object AppModule {

    @Provides fun ioDispatcher(): CoroutineDispatcher = Dispatchers.IO


}