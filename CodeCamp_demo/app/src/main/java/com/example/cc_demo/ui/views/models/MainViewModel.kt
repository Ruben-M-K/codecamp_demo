package com.example.cc_demo.ui.views.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cc_demo.data.repositories.NetworkRepository
import com.example.cc_demo.ui.views.states.MainViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val networkRepository: NetworkRepository
): ViewModel(){
    private val _uiStateFlow = MutableStateFlow(MainViewState())
    val uiStateFlow: StateFlow<MainViewState> = _uiStateFlow

    init {
        viewModelScope.launch {
            networkRepository.networkStatus.collect {
                _uiStateFlow.value = _uiStateFlow.value.copy(connection = it)
            }
        }
    }
}