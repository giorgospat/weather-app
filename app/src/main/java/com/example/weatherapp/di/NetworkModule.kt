package com.example.weatherapp.di

import com.example.data.backend.*
import com.example.data.builder.RetrofitBuilder
import com.example.weatherapp.BuildConfig
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder().build()
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor? {
        return if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
            null
        }
    }

    @Provides
    @Singleton
    fun provideBackendUrlProvider(): BackendConfigProvider = BackendConfigProviderImpl()

    @Provides
    @Singleton
    fun provideQueryInterceptor(
        configProvider: BackendConfigProvider
    ): QueryInterceptor = QueryInterceptor(configProvider)

    @Provides
    @Singleton
    fun provideApiService(
        moshi: Moshi,
        urlProvider: BackendConfigProvider,
        httpLoggingInterceptor: HttpLoggingInterceptor?,
        queryInterceptor: QueryInterceptor
    ): WeatherApi {
        return RetrofitBuilder().makeRetrofitService(
            apiClass = WeatherApi::class.java,
            baseUrl = urlProvider.baseUrl(),
            connectTimeoutSeconds = NetworkConstants.connectTimeoutSeconds,
            readTimeoutSeconds = NetworkConstants.readTimeoutSeconds,
            writeTimeoutSeconds = NetworkConstants.writeTimeoutSeconds,
            interceptors = listOf(httpLoggingInterceptor, queryInterceptor),
            moshi = moshi
        )
    }

}