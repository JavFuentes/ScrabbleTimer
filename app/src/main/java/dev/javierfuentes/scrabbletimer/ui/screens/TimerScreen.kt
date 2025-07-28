package dev.javierfuentes.scrabbletimer.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.javierfuentes.scrabbletimer.R
import dev.javierfuentes.scrabbletimer.data.TimerState
import dev.javierfuentes.scrabbletimer.ui.components.TimerControlButtons
import dev.javierfuentes.scrabbletimer.ui.components.TimerDisplay
import dev.javierfuentes.scrabbletimer.ui.theme.ScrabbleTimerTheme
import dev.javierfuentes.scrabbletimer.ui.viewmodel.TimerViewModel
import dev.javierfuentes.scrabbletimer.utils.TimerNotificationHelper

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimerScreen(
    modifier: Modifier = Modifier,
    selectedMinutes: Int,
    onBackClick: () -> Unit = {},
    viewModel: TimerViewModel = viewModel()
) {
    val context = LocalContext.current
    val notificationHelper = remember { TimerNotificationHelper(context) }
    val uiState by viewModel.uiState.collectAsState()
    
    // Initialize timer with selected minutes
    LaunchedEffect(selectedMinutes) {
        viewModel.initializeTimer(selectedMinutes)
    }
    
    // Set timer finished callback
    LaunchedEffect(Unit) {
        viewModel.setOnTimerFinishedCallback {
            notificationHelper.playTimerFinishedNotification()
        }
    }
    
    // Clean up notification helper when composable is disposed
    DisposableEffect(notificationHelper) {
        onDispose {
            notificationHelper.release()
        }
    }
    
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.screenWidthDp > configuration.screenHeightDp
    val isCompactHeight = configuration.screenHeightDp < 500
    
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
                .padding(horizontal = if (isLandscape || isCompactHeight) 16.dp else 24.dp)
                .padding(vertical = if (isLandscape || isCompactHeight) 8.dp else 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TimerDisplay(
                minutes = uiState.displayMinutes,
                seconds = uiState.displaySeconds,
                timerState = uiState.timerState
            )
            
            Spacer(
                modifier = Modifier.height(
                    when {
                        isCompactHeight -> 12.dp
                        isLandscape -> 16.dp
                        else -> 24.dp
                    }
                )
            )
            
            TimerControlButtons(
                isPlaying = uiState.timerState == TimerState.RUNNING,
                onPlayPauseClick = { 
                    viewModel.togglePlayPause()
                },
                onResetClick = { 
                    viewModel.resetTimer()
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