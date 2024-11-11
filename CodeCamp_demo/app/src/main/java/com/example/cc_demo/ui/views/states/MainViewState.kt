package com.example.cc_demo.ui.views.states

import com.example.cc_demo.data.model.WeatherData
import kotlinx.coroutines.flow.Flow

data class MainViewState(
    val connection: Boolean = false,
    val weatherData: Flow<WeatherData>? = null
)
