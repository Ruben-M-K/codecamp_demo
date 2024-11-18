package com.example.cc_demo.ui.views.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.WorkManager
import com.example.cc_demo.data.repositories.NetworkRepository
import com.example.cc_demo.data.repositories.WeatherRepository
import com.example.cc_demo.ui.views.states.MainViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val networkRepository: NetworkRepository,
    private val weatherRepository: WeatherRepository,
    private val workManger: WorkManager,
): ViewModel(){
    private val _uiStateFlow = MutableStateFlow(MainViewState())
    val uiStateFlow: StateFlow<MainViewState> = _uiStateFlow

    val weatherData = weatherRepository.weatherFlow

    init {
        viewModelScope.launch {
            networkRepository.networkStatus.collect {
                _uiStateFlow.value = _uiStateFlow.value.copy(connection = it)
            }
        }

        viewModelScope.launch{
            _uiStateFlow.value = _uiStateFlow.value.copy(weatherData = weatherData)
        }
    }

    fun fetchWeatherData(){
        viewModelScope.launch {
            weatherRepository.fetchWeatherData()
        }
    }

}