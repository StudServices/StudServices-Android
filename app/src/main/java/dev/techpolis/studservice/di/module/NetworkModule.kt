package dev.techpolis.studservice.di.module

import dagger.Module
import dagger.Provides
import dev.techpolis.studservice.api.NetworkAPI
import dev.techpolis.studservice.utils.Constants.Companion.API_BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
object NetworkModule {

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
//            .protocols(Collections.singletonList(Protocol.HTTP_1_1))
            .build()

    @Singleton
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory =
        GsonConverterFactory.create()

    @Singleton
    @Provides
    fun provideApiService(
        client: OkHttpClient,
        converterFactory: GsonConverterFactory
    ): NetworkAPI =
        Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .client(client)
            .addConverterFactory(converterFactory)
            .build()
            .create(NetworkAPI::class.java)
}