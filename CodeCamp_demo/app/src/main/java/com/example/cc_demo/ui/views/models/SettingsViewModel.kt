package com.example.cc_demo.ui.views.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.cc_demo.data.repositories.SettingsRepository
import com.example.cc_demo.ui.views.states.SettingsViewState
import com.example.cc_demo.workers.AppWorker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

private const val WORK_KEY = "app_worker"

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository,
    private val workManger: WorkManager,
) : ViewModel() {

    private val _uiStateFlow = MutableStateFlow(SettingsViewState())
    val uiStateFlow: StateFlow<SettingsViewState> = _uiStateFlow

    val setting = settingsRepository.option1
    init {
        viewModelScope.launch {
            _uiStateFlow.value = _uiStateFlow.value.copy(connection = setting)
        }
    }

    fun saveOption(option: Boolean) {
        viewModelScope.launch {
            settingsRepository.saveOption1(option)
            if (option) {
                startWork()
            } else {
                stopWork()
            }
        }
    }

    private fun startWork() {
        val weatherWorkRequest =
            PeriodicWorkRequestBuilder<AppWorker>(15, TimeUnit.MINUTES).build()
        workManger.enqueueUniquePeriodicWork(
            WORK_KEY, ExistingPeriodicWorkPolicy.CANCEL_AND_REENQUEUE, weatherWorkRequest
        )
    }

    private fun stopWork() {
        workManger.cancelUniqueWork(WORK_KEY)
    }

}