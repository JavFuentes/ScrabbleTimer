package dev.javierfuentes.scrabbletimer.utils

import android.content.Context
import android.media.ToneGenerator
import android.media.AudioManager
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager

class TimerNotificationHelper(private val context: Context) {
    
    private val vibrator: Vibrator? by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val vibratorManager = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
            vibratorManager.defaultVibrator
        } else {
            @Suppress("DEPRECATION")
            context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        }
    }
    
    private val toneGenerator: ToneGenerator? by lazy {
        try {
            ToneGenerator(AudioManager.STREAM_ALARM, 100)
        } catch (e: RuntimeException) {
            null
        }
    }
    
    fun playTimerFinishedNotification() {
        playSound()
        vibrate()
    }
    
    private fun playSound() {
        toneGenerator?.let { generator ->
            try {
                generator.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 1000)
            } catch (e: Exception) {
                // Fallback to a shorter beep if the first tone fails
                generator.startTone(ToneGenerator.TONE_PROP_BEEP, 500)
            }
        }
    }
    
    private fun vibrate() {
        vibrator?.let { vib ->
            // Create a vibration pattern: pause, vibrate, pause, vibrate
            val timings = longArrayOf(0, 500, 200, 500, 200, 500)
            val amplitudes = intArrayOf(0, 255, 0, 255, 0, 255)
            val vibrationEffect = VibrationEffect.createWaveform(timings, amplitudes, -1)
            vib.vibrate(vibrationEffect)
        }
    }
    
    fun release() {
        toneGenerator?.release()
    }
}