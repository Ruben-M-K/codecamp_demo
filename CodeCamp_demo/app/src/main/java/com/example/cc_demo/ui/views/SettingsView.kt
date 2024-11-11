package com.example.cc_demo.ui.views

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cc_demo.ui.views.models.SettingsViewModel

@Preview
@Composable
fun SettingsView(viewModel: SettingsViewModel = hiltViewModel()) {
    val uiState = viewModel.uiStateFlow.collectAsState().value
    val switch = uiState.connection.collectAsState(initial = false).value
    SettingsSwitch(onCheckedChange = { viewModel.saveOption(it) }, checked = switch)
}

@Composable
fun SettingsSwitch(checked: Boolean = false, onCheckedChange: (Boolean) -> Unit) {
    Row {
        Text(text = "Turn on the Worker for passive weather updates")
        Spacer(modifier = Modifier.padding(8.dp))
        Switch(checked = checked, onCheckedChange = onCheckedChange)
    }
}