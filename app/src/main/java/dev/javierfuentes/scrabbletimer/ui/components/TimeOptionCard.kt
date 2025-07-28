package dev.javierfuentes.scrabbletimer.ui.components

import androidx.compose.foundation.clickable
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
import dev.javierfuentes.scrabbletimer.data.TimeOption
import dev.javierfuentes.scrabbletimer.ui.theme.ScrabbleTimerTheme

@Composable
fun TimeOptionCard(
    timeOption: TimeOption,
    onTimeSelected: (TimeOption) -> Unit,
    modifier: Modifier = Modifier
) {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.screenWidthDp > configuration.screenHeightDp
    val isCompactHeight = configuration.screenHeightDp < 500
    
    // Adaptive sizing for landscape
    val mainFontSize = when {
        isLandscape && isCompactHeight -> 20.sp
        isLandscape -> 24.sp
        else -> 28.sp
    }
    
    val descriptionFontSize = when {
        isLandscape && isCompactHeight -> 9.sp
        isLandscape -> 10.sp
        else -> 12.sp
    }
    
    val cardPadding = when {
        isLandscape && isCompactHeight -> 8.dp
        isLandscape -> 12.dp
        else -> 16.dp
    }
    
    val spacerSize = when {
        isLandscape -> 6.dp
        else -> 4.dp
    }
    
    Card(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .clickable { onTimeSelected(timeOption) },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiary
        )
    ) {
        if (isLandscape) {
            // Horizontal layout for landscape
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(cardPadding),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(timeOption.displayTextRes),
                    fontSize = mainFontSize,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onTertiary,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.width(spacerSize))
                Text(
                    text = stringResource(timeOption.descriptionRes),
                    fontSize = descriptionFontSize,
                    color = MaterialTheme.colorScheme.onTertiary,
                    textAlign = TextAlign.Center
                )
            }
        } else {
            // Vertical layout for portrait (original)
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(cardPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(timeOption.displayTextRes),
                    fontSize = mainFontSize,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onTertiary,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(spacerSize))
                Text(
                    text = stringResource(timeOption.descriptionRes),
                    fontSize = descriptionFontSize,
                    color = MaterialTheme.colorScheme.onTertiary,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TimeOptionCardPreview() {
    ScrabbleTimerTheme {
        TimeOptionCard(
            timeOption = TimeOption(3, R.string.time_3_min, R.string.time_3_min_desc),
            onTimeSelected = {}
        )
    }
}