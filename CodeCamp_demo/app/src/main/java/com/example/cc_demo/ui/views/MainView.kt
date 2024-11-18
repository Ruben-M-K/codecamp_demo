package com.example.cc_demo.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cc_demo.R
import com.example.cc_demo.ui.views.models.MainViewModel
import dagger.hilt.android.qualifiers.ApplicationContext

@Composable
fun MainView(
    viewModel: MainViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiStateFlow.collectAsState().value
    val weather = uiState.weatherData?.collectAsState(initial = null)?.value

    LaunchedEffect(key1 = uiState.connection, block = {
        if (uiState.connection) {
            viewModel.fetchWeatherData()
        }
    })


    Column {


        Text("Main View")
        Text("Connection: ${uiState.connection}")
        Button(onClick = { viewModel.fetchWeatherData() }) {
            Text("Fetch Weather Data")
        }

        Text(text = "Temperature: ${weather?.temperature}")
    }

}