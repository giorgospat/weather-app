package com.example.weatherapp.di

import com.example.data.feature.home.ForecastRepositoryImpl
import com.example.data.feature.home.cache.ForecastCache
import com.example.data.feature.home.cache.ForecastCacheImpl
import com.example.data.feature.location.LocationRepositoryImpl
import com.example.data.feature.location.LocationStoreRepositoryImpl
import com.example.domain.feature.home.ForecastRepository
import com.example.domain.feature.location.LocationRepository
import com.example.domain.feature.location.store.LocationStoreRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindWeatherRepo(repo: ForecastRepositoryImpl): ForecastRepository

    @Binds
    abstract fun bindLocationStoreRepository(repo: LocationStoreRepositoryImpl): LocationStoreRepository

    @Binds
    abstract fun bindLocationRepository(repo: LocationRepositoryImpl): LocationRepository

    @Binds
    @Singleton
    abstract fun bindForecastCache(cache: ForecastCacheImpl): ForecastCache

}