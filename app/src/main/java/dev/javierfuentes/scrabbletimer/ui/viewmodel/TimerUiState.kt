package dev.javierfuentes.scrabbletimer.ui.viewmodel

import dev.javierfuentes.scrabbletimer.data.TimerState

data class TimerUiState(
    val timerState: TimerState = TimerState.IDLE,
    val remainingSeconds: Int = 0,
    val totalSeconds: Int = 0,
    val selectedMinutes: Int = 0,
    val displayMinutes: Int = 0,
    val displaySeconds: Int = 0
) {
    companion object {
        fun initial(selectedMinutes: Int): TimerUiState {
            val totalSeconds = selectedMinutes * 60
            return TimerUiState(
                timerState = TimerState.IDLE,
                remainingSeconds = totalSeconds,
                totalSeconds = totalSeconds,
                selectedMinutes = selectedMinutes,
                displayMinutes = selectedMinutes,
                displaySeconds = 0
            )
        }
    }
}