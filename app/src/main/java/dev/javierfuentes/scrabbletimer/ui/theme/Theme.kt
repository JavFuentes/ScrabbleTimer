package dev.javierfuentes.scrabbletimer.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

// Green-based Dark Color Scheme
private val DarkGreenColorScheme = darkColorScheme(
    primary = Green3,                    // Light green for visibility
    onPrimary = Green,                   // Dark green text
    primaryContainer = GreenContainerDark,
    onPrimaryContainer = Green3,
    
    secondary = Green2,
    onSecondary = White,
    secondaryContainer = Green,
    onSecondaryContainer = GreenContainer,
    
    tertiary = Green3,
    onTertiary = White,
    
    background = Green,                  // Same dark green as light mode
    onBackground = White,                // White text on dark green
    surface = Green,                     // Same dark green surface
    onSurface = White,                   // White text on dark green surface
    
    error = Color(0xFFFFB4AB),          // Material error for dark
    onError = Color(0xFF690005),
    errorContainer = Green3,
    onErrorContainer = Color(0xFFFFDAD6)
)

// Green-based Light Color Scheme  
private val LightGreenColorScheme = lightColorScheme(
    primary = Green2,                    // Medium green primary
    onPrimary = White,
    primaryContainer = GreenContainer,   // Light green container
    onPrimaryContainer = Green,          // Dark green text
    
    secondary = Green3,                  // Light green secondary
    onSecondary = White,
    secondaryContainer = Color(0xFFD4F0D8), // Very light green
    onSecondaryContainer = Green,
    
    tertiary = Square,
    onTertiary = Black,
    
    background = Green,                  // Dark green background
    onBackground = White,                // White text on dark green
    surface = Green,                     // Dark green surface
    onSurface = White,                   // White text on dark green surface
    
    error = Color(0xFFBA1A1A),         // Material error for light
    onError = White,
    errorContainer = Green2,
    onErrorContainer = Color(0xFF410002)


)

@Composable
fun ScrabbleTimerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color disabled to use custom green theme
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkGreenColorScheme
        else -> LightGreenColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}