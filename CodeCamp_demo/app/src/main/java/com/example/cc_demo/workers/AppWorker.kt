package com.example.cc_demo.workers

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import com.example.cc_demo.data.repositories.WeatherRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.coroutineScope

@HiltWorker
class AppWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    private val weatherRepository: WeatherRepository
) : CoroutineWorker(context, workerParams) {


    override suspend fun doWork(): Result {
        coroutineScope {
        weatherRepository.fetchWeatherData()
        }
        return Result.success()
    }
}