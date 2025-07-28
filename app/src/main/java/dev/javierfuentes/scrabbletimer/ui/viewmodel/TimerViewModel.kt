package dev.javierfuentes.scrabbletimer.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.javierfuentes.scrabbletimer.data.TimerState
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TimerViewModel : ViewModel() {
    
    private val _uiState = MutableStateFlow(TimerUiState())
    val uiState: StateFlow<TimerUiState> = _uiState.asStateFlow()
    
    private var timerJob: Job? = null
    private var onTimerFinished: (() -> Unit)? = null
    
    fun initializeTimer(selectedMinutes: Int) {
        if (_uiState.value.selectedMinutes == 0) {
            _uiState.value = TimerUiState.initial(selectedMinutes)
        }
    }
    
    fun setOnTimerFinishedCallback(callback: () -> Unit) {
        onTimerFinished = callback
    }
    
    fun startTimer() {
        val currentState = _uiState.value
        
        if (currentState.timerState == TimerState.FINISHED) {
            resetTimer()
            return
        }
        
        _uiState.value = currentState.copy(timerState = TimerState.RUNNING)
        
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            while (_uiState.value.timerState == TimerState.RUNNING && _uiState.value.remainingSeconds > 0) {
                delay(1000L)
                val current = _uiState.value
                if (current.timerState == TimerState.RUNNING) {
                    val newRemainingSeconds = current.remainingSeconds - 1
                    if (newRemainingSeconds <= 0) {
                        _uiState.value = current.copy(
                            timerState = TimerState.FINISHED,
                            remainingSeconds = 0,
                            displayMinutes = 0,
                            displaySeconds = 0
                        )
                        onTimerFinished?.invoke()
                    } else {
                        _uiState.value = current.copy(
                            remainingSeconds = newRemainingSeconds,
                            displayMinutes = newRemainingSeconds / 60,
                            displaySeconds = newRemainingSeconds % 60
                        )
                    }
                }
            }
        }
    }
    
    fun pauseTimer() {
        timerJob?.cancel()
        _uiState.value = _uiState.value.copy(timerState = TimerState.PAUSED)
    }
    
    fun resetTimer() {
        timerJob?.cancel()
        val totalSeconds = _uiState.value.totalSeconds
        _uiState.value = _uiState.value.copy(
            timerState = TimerState.IDLE,
            remainingSeconds = totalSeconds,
            displayMinutes = totalSeconds / 60,
            displaySeconds = 0
        )
    }
    
    fun togglePlayPause() {
        when (_uiState.value.timerState) {
            TimerState.IDLE -> startTimer()
            TimerState.RUNNING -> pauseTimer()
            TimerState.PAUSED -> startTimer()
            TimerState.FINISHED -> {
                resetTimer()
                startTimer()
            }
        }
    }
    
    override fun onCleared() {
        super.onCleared()
        timerJob?.cancel()
    }
}