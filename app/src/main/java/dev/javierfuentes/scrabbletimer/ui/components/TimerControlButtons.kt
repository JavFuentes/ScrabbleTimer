package dev.javierfuentes.scrabbletimer.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.javierfuentes.scrabbletimer.R
import dev.javierfuentes.scrabbletimer.ui.theme.ScrabbleTimerTheme

@Composable
fun TimerControlButtons(
    isPlaying: Boolean,
    onPlayPauseClick: () -> Unit,
    onResetClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.screenWidthDp > configuration.screenHeightDp
    val isCompactHeight = configuration.screenHeightDp < 500
    
    // Adaptive sizing
    val buttonHeight = when {
        isCompactHeight -> 48.dp
        isLandscape -> 56.dp
        else -> 64.dp
    }
    
    val iconSize = when {
        isCompactHeight -> 20.dp
        else -> 24.dp
    }
    
    val fontSize = when {
        isCompactHeight -> 14.sp
        isLandscape -> 16.sp
        else -> 18.sp
    }
    
    val spacing = when {
        isCompactHeight -> 12.dp
        isLandscape -> 16.dp
        else -> 24.dp
    }
    
    // Always use horizontal layout but with adaptive spacing
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(spacing, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = onPlayPauseClick,
            modifier = Modifier
                .weight(1f)
                .height(buttonHeight),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isPlaying) 
                    MaterialTheme.colorScheme.onSecondaryContainer
                else
                    MaterialTheme.colorScheme.secondary,
                contentColor = if (isPlaying)
                    MaterialTheme.colorScheme.onSurface
                else
                    MaterialTheme.colorScheme.onSurface
            )
        ) {
            Icon(
                imageVector = if (isPlaying) Icons.Filled.Pause else Icons.Default.PlayArrow,
                contentDescription = stringResource(R.string.play_pause_button),
                modifier = Modifier.size(iconSize)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = stringResource(if (isPlaying) R.string.pause else R.string.play),
                fontSize = fontSize,
                fontWeight = FontWeight.Medium
            )
        }
        
        Button(
            onClick = onResetClick,
            modifier = Modifier
                .weight(1f)
                .height(buttonHeight),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = MaterialTheme.colorScheme.onSurface,
                containerColor = MaterialTheme.colorScheme.secondary
            )
        ) {
            Icon(
                imageVector = Icons.Default.Refresh,
                contentDescription = stringResource(R.string.reset_button),
                modifier = Modifier.size(iconSize)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = stringResource(R.string.reset),
                fontSize = fontSize,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TimerControlButtonsPreview() {
    ScrabbleTimerTheme {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(stringResource(R.string.preview_playing_state))
            TimerControlButtons(
                isPlaying = true,
                onPlayPauseClick = {},
                onResetClick = {}
            )
            
            Text(stringResource(R.string.preview_paused_state))
            TimerControlButtons(
                isPlaying = false,
                onPlayPauseClick = {},
                onResetClick = {}
            )
        }
    }
}