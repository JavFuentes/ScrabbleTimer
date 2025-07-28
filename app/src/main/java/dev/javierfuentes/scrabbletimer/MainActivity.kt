package dev.javierfuentes.scrabbletimer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.core.view.WindowCompat
import dev.javierfuentes.scrabbletimer.ui.navigation.ScrabbleNavigation
import dev.javierfuentes.scrabbletimer.ui.theme.Green
import dev.javierfuentes.scrabbletimer.ui.theme.ScrabbleTimerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ScrabbleTimerTheme {
                val window = this@MainActivity.window
                SideEffect {
                    window.navigationBarColor = Green.toArgb()
                    WindowCompat.getInsetsController(window, window.decorView).apply {
                        isAppearanceLightNavigationBars = false
                    }
                }
                ScrabbleNavigation()
            }
        }
    }
}