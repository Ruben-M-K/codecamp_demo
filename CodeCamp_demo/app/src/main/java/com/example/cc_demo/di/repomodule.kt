@file:Suppress("NAME_SHADOWING")

package com.example.cc_demo.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.room.Room
import androidx.work.WorkManager
import com.example.cc_demo.data.database.WeatherDatabase
import com.example.cc_demo.data.network.WeatherService
import com.example.cc_demo.data.repositories.NetworkRepository
import com.example.cc_demo.data.repositories.SettingsRepository
import com.example.cc_demo.data.repositories.WeatherRepository
import com.example.cc_demo.util.dataStore
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object repomodule {

    data class RetroFitHolder(val weatherRetrofit: Retrofit)
    @Provides
    fun providesRetroFit(): RetroFitHolder {

        val weatherRetrofit =
            Retrofit.Builder().baseUrl("https://api.open-meteo.com/v1/").addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                )
            ).build()



        return RetroFitHolder(weatherRetrofit)
    }

        @Provides
    fun providesNetworkRepository(@ApplicationContext context: Context): NetworkRepository {
        return NetworkRepository(context)
    }
    @Provides
    fun provideWeatherRepo(api: WeatherService, weatherDb: WeatherDatabase): WeatherRepository {
        val weatherDao = weatherDb.weatherDao()
        return WeatherRepository(api, weatherDao)
    }

    @Provides
    fun providesDatabase(@ApplicationContext context: Context): WeatherDatabase {
        return Room.databaseBuilder(
            context, WeatherDatabase::class.java, "weatherdatabase"
        ).build()
    }
    @Provides
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences>{
        return context.dataStore
    }
    @Provides
    fun providesSettingsRepo(dataStore: DataStore<Preferences>): SettingsRepository {
        val dataStore = dataStore
        return SettingsRepository(dataStore)
    }
    @Provides
    fun providesWorkmanager(@ApplicationContext context: Context): WorkManager {
        return WorkManager.getInstance(context)
    }
}