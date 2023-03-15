package com.example.weatherapp.di

import android.content.Context
import androidx.room.Room
import com.example.data.database.AppDatabase
import com.example.data.database.LocationDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "WeatherDb"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideDbDao(appDatabase: AppDatabase): LocationDao {
        return appDatabase.locationDao()
    }

}