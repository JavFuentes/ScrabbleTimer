package dev.javierfuentes.scrabbletimer.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
    // Define colors based on timer state
    val (containerColor, textColor) = when (timerState) {
        TimerState.IDLE -> MaterialTheme.colorScheme.primaryContainer to MaterialTheme.colorScheme.onPrimaryContainer
        TimerState.RUNNING -> MaterialTheme.colorScheme.primary to MaterialTheme.colorScheme.onPrimary
        TimerState.PAUSED -> MaterialTheme.colorScheme.secondaryContainer to MaterialTheme.colorScheme.onSecondaryContainer
        TimerState.FINISHED -> MaterialTheme.colorScheme.errorContainer to MaterialTheme.colorScheme.onErrorContainer
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
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.time_format, minutes, seconds),
                fontSize = 84.sp,
                fontWeight = FontWeight.Bold,
                color = textColor,
                textAlign = TextAlign.Center,
                letterSpacing = 4.sp
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