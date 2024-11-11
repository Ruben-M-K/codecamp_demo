package com.example.cc_demo.ui.views.states

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class SettingsViewState(
    val connection: Flow<Boolean> = emptyFlow()
)
