package dev.javierfuentes.scrabbletimer.data

enum class TimerState {
    IDLE,     // Timer not started
    RUNNING,  // Timer is counting down
    PAUSED,   // Timer is paused
    FINISHED  // Timer reached 00:00
}