package com.example.cc_demo.data.repositories

import com.example.cc_demo.data.database.WeatherDao
import com.example.cc_demo.data.network.WeatherService
import com.example.cc_demo.data.model.WeatherData
class WeatherRepository(private val api: WeatherService, private val weatherDao: WeatherDao) {

    val weatherFlow = weatherDao.getWeather()

    suspend fun fetchWeatherData() {
        val resp = api.getWeather()
        val data = if (resp.isSuccessful) {
            resp.body()
        } else {
            null
        }
        val databaseData = data?.let {
            com.example.cc_demo.data.database.model.WeatherData(
                temperature = it.temperature
            )
        }
        if (databaseData != null) {
            weatherDao.insert(databaseData)
        }
    }


}