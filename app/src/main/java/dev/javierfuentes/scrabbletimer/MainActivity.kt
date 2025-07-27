package dev.javierfuentes.scrabbletimer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dev.javierfuentes.scrabbletimer.ui.navigation.ScrabbleNavigation
import dev.javierfuentes.scrabbletimer.ui.theme.ScrabbleTimerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ScrabbleTimerTheme {
                ScrabbleNavigation()
            }
        }
    }
}