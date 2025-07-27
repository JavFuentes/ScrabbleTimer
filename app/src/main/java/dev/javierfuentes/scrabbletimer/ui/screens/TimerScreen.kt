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
import androidx.compose.ui.unit.sp
import dev.javierfuentes.scrabbletimer.R
import dev.javierfuentes.scrabbletimer.ui.components.TimerControlButtons
import dev.javierfuentes.scrabbletimer.ui.components.TimerDisplay
import dev.javierfuentes.scrabbletimer.ui.theme.ScrabbleTimerTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimerScreen(
    selectedMinutes: Int,
    onBackClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    var isPlaying by remember { mutableStateOf(false) }
    var currentMinutes by remember { mutableIntStateOf(selectedMinutes) }
    var currentSeconds by remember { mutableIntStateOf(0) }
    
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
                minutes = currentMinutes,
                seconds = currentSeconds,
                modifier = Modifier.weight(1f, fill = false)
            )
            
            TimerControlButtons(
                isPlaying = isPlaying,
                onPlayPauseClick = { 
                    isPlaying = !isPlaying
                },
                onResetClick = { 
                    isPlaying = false
                    currentMinutes = selectedMinutes
                    currentSeconds = 0
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