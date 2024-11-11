package com.example.cc_demo.data.network

import com.example.cc_demo.data.model.WeatherData
import retrofit2.Response
import retrofit2.http.GET

interface WeatherService {

    @GET("forecast?latitude=52.52&longitude=13.41&current=temperature,precipitation_probability")
    suspend fun getWeather(): Response<WeatherData?>
}