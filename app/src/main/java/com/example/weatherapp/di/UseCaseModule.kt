package com.example.weatherapp.di

import com.example.domain.feature.home.ForecastUseCase
import com.example.domain.feature.home.ForecastUseCaseImpl
import com.example.domain.feature.location.search.LocationSearchUseCase
import com.example.domain.feature.location.search.LocationSearchUseCaseImpl
import com.example.domain.feature.location.store.LocationStoreUseCase
import com.example.domain.feature.location.store.LocationStoreUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCaseModule {

    @Binds
    @ViewModelScoped
    abstract fun bindForecastUseCase(useCase: ForecastUseCaseImpl): ForecastUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindLocationStoreUseCase(useCase: LocationStoreUseCaseImpl): LocationStoreUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindLocationSearchUseCase(useCase: LocationSearchUseCaseImpl): LocationSearchUseCase

}