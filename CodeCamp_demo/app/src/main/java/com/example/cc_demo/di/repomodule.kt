package com.example.cc_demo.di

import android.content.Context
import com.example.cc_demo.data.repositories.NetworkRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object repomodule {

    data class RetroFitHolder(val weatherRetrofit: Retrofit, val quotesRetrofit: Retrofit)

    @Provides
    fun providesNetworkRepository(@ApplicationContext context: Context): NetworkRepository {
        return NetworkRepository(context)
    }
}