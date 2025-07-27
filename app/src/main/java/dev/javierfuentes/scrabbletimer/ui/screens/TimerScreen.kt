package dev.javierfuentes.scrabbletimer.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.javierfuentes.scrabbletimer.R
import dev.javierfuentes.scrabbletimer.data.TimerState
import dev.javierfuentes.scrabbletimer.ui.components.TimerControlButtons
import dev.javierfuentes.scrabbletimer.ui.components.TimerDisplay
import dev.javierfuentes.scrabbletimer.ui.theme.ScrabbleTimerTheme
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimerScreen(
    modifier: Modifier = Modifier,
    selectedMinutes: Int,
    onBackClick: () -> Unit = {},
) {
    // Timer state management
    var timerState by remember { mutableStateOf(TimerState.IDLE) }
    val totalSeconds = selectedMinutes * 60
    var remainingSeconds by remember { mutableIntStateOf(totalSeconds) }
    
    // Convert seconds to minutes and seconds for display
    val displayMinutes = remainingSeconds / 60
    val displaySeconds = remainingSeconds % 60
    
    // Countdown logic
    LaunchedEffect(timerState, remainingSeconds) {
        if (timerState == TimerState.RUNNING && remainingSeconds > 0) {
            delay(1000L)
            remainingSeconds--
        } else if (remainingSeconds == 0 && timerState == TimerState.RUNNING) {
            timerState = TimerState.FINISHED
        }
    }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.selected_time_minutes, selectedMinutes),
                        fontWeight = FontWeight.Medium
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back_button)
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            TimerDisplay(
                minutes = displayMinutes,
                seconds = displaySeconds,
                timerState = timerState,
                modifier = Modifier.weight(1f, fill = false)
            )
            
            TimerControlButtons(
                isPlaying = timerState == TimerState.RUNNING,
                onPlayPauseClick = { 
                    timerState = when (timerState) {
                        TimerState.IDLE -> TimerState.RUNNING
                        TimerState.RUNNING -> TimerState.PAUSED
                        TimerState.PAUSED -> TimerState.RUNNING
                        TimerState.FINISHED -> {
                            // Reset and start from finished state
                            remainingSeconds = totalSeconds
                            TimerState.RUNNING
                        }
                    }
                },
                onResetClick = { 
                    timerState = TimerState.IDLE
                    remainingSeconds = totalSeconds
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TimerScreenPreview() {
    ScrabbleTimerTheme {
        TimerScreen(
            selectedMinutes = 3,
            onBackClick = {}
        )
    }
}