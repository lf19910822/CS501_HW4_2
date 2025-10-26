package com.example.hw4_2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class CounterState(
    val count: Int = 0,
    val isAutoMode: Boolean = false,
    val autoIncrementInterval: Long = 3000L
)

class CounterViewModel : ViewModel() {
    private val _state = MutableStateFlow(CounterState())
    val state: StateFlow<CounterState> = _state.asStateFlow()

    private var autoIncrementJob: Job? = null

    fun increment() {
        _state.value = _state.value.copy(count = _state.value.count + 1)
    }

    fun decrement() {
        _state.value = _state.value.copy(count = _state.value.count - 1)
    }

    fun reset() {
        _state.value = _state.value.copy(count = 0)
    }

    fun toggleAutoMode() {
        val newAutoMode = !_state.value.isAutoMode
        _state.value = _state.value.copy(isAutoMode = newAutoMode)

        if (newAutoMode) {
            startAutoIncrement()
        } else {
            stopAutoIncrement()
        }
    }

    fun updateInterval(interval: Long) {
        _state.value = _state.value.copy(autoIncrementInterval = interval)
        if (_state.value.isAutoMode) {
            stopAutoIncrement()
            startAutoIncrement()
        }
    }

    private fun startAutoIncrement() {
        autoIncrementJob = viewModelScope.launch {
            while (true) {
                delay(_state.value.autoIncrementInterval)
                _state.value = _state.value.copy(count = _state.value.count + 1)
            }
        }
    }

    private fun stopAutoIncrement() {
        autoIncrementJob?.cancel()
        autoIncrementJob = null
    }

    override fun onCleared() {
        super.onCleared()
        stopAutoIncrement()
    }
}

