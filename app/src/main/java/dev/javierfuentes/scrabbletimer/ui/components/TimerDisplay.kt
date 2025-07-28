package dev.javierfuentes.scrabbletimer.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.javierfuentes.scrabbletimer.R
import dev.javierfuentes.scrabbletimer.data.TimerState
import dev.javierfuentes.scrabbletimer.ui.theme.ScrabbleTimerTheme

@Composable
fun TimerDisplay(
    modifier: Modifier = Modifier,
    minutes: Int,
    seconds: Int,
    timerState: TimerState = TimerState.IDLE,
) {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.screenWidthDp > configuration.screenHeightDp
    val isCompactHeight = configuration.screenHeightDp < 500
    
    // Define colors based on timer state
    val (containerColor, textColor) = when (timerState) {
        TimerState.IDLE -> MaterialTheme.colorScheme.tertiary to MaterialTheme.colorScheme.onTertiary
        TimerState.RUNNING -> MaterialTheme.colorScheme.tertiary to MaterialTheme.colorScheme.onTertiary
        TimerState.PAUSED -> MaterialTheme.colorScheme.tertiary to MaterialTheme.colorScheme.onTertiary
        TimerState.FINISHED -> MaterialTheme.colorScheme.errorContainer to MaterialTheme.colorScheme.onSurface
    }
    
    // Adaptive sizing based on orientation and screen size
    val fontSize = when {
        isCompactHeight -> 48.sp
        isLandscape -> 64.sp
        else -> 84.sp
    }
    
    val padding = when {
        isCompactHeight -> 16.dp
        isLandscape -> 24.dp
        else -> 32.dp
    }
    
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = containerColor
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.time_format, minutes, seconds),
                fontSize = fontSize,
                fontWeight = FontWeight.Bold,
                color = textColor,
                textAlign = TextAlign.Center,
                letterSpacing = if (isCompactHeight) 2.sp else 4.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TimerDisplayPreview() {
    ScrabbleTimerTheme {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TimerDisplay(minutes = 3, seconds = 0, timerState = TimerState.IDLE)
            TimerDisplay(minutes = 2, seconds = 30, timerState = TimerState.RUNNING)
            TimerDisplay(minutes = 1, seconds = 15, timerState = TimerState.PAUSED)
            TimerDisplay(minutes = 0, seconds = 0, timerState = TimerState.FINISHED)
        }
    }
}