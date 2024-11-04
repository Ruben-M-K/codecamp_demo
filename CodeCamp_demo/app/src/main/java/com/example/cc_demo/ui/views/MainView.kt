package com.example.cc_demo.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cc_demo.R
import com.example.cc_demo.ui.views.models.MainViewModel

@Composable
fun MainView(
    viewModel: MainViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiStateFlow.collectAsState().value
    Column {


        Text("Main View")
        Text("Connection: ${uiState.connection}")



    }

}