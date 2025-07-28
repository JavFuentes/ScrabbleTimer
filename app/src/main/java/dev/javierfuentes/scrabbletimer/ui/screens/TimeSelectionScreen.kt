package dev.javierfuentes.scrabbletimer.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.javierfuentes.scrabbletimer.R
import dev.javierfuentes.scrabbletimer.data.TimeOption
import dev.javierfuentes.scrabbletimer.ui.components.TimeOptionCard
import dev.javierfuentes.scrabbletimer.ui.theme.ScrabbleTimerTheme

@Composable
fun AutoSizeText(
    modifier: Modifier = Modifier,
    text: String,
    style: TextStyle,
    maxLines: Int = 1,
) {
    var scaledTextStyle by remember { mutableStateOf(style) }
    var readyToDraw by remember { mutableStateOf(false) }

    Text(
        text = text,
        modifier = modifier.drawWithContent {
            if (readyToDraw) drawContent()
        },
        style = scaledTextStyle,
        maxLines = maxLines,
        softWrap = false,
        onTextLayout = { textLayoutResult ->
            if (textLayoutResult.didOverflowWidth && scaledTextStyle.fontSize >= 10.sp) {
                scaledTextStyle = scaledTextStyle.copy(
                    fontSize = scaledTextStyle.fontSize * 0.9f
                )
            } else {
                readyToDraw = true
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimeSelectionScreen(
    onTimeSelected: (TimeOption) -> Unit,
    modifier: Modifier = Modifier
) {
    val timeOptions = TimeOption.getTimeOptions()
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.screenWidthDp > configuration.screenHeightDp
    val isCompactHeight = configuration.screenHeightDp < 500

    Scaffold { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(
                    horizontal = 16.dp,
                    vertical = if (isLandscape || isCompactHeight) 8.dp else 16.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AutoSizeText(
                text = stringResource(R.string.time_selection_subtitle),
                style = TextStyle(
                    fontSize = when {
                        isCompactHeight -> 16.sp
                        isLandscape -> 18.sp
                        else -> 20.sp
                    },
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurface
                ),
                maxLines = if (isLandscape) 2 else 1,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
                    .padding(
                        bottom = when {
                            isCompactHeight -> 8.dp
                            isLandscape -> 12.dp
                            else -> 16.dp
                        }
                    )
            )
            
            Column(
                verticalArrangement = Arrangement.spacedBy(
                    when {
                        isCompactHeight -> 6.dp
                        isLandscape -> 8.dp
                        else -> 12.dp
                    }
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                timeOptions.forEach { timeOption ->
                    TimeOptionCard(
                        timeOption = timeOption,
                        onTimeSelected = onTimeSelected,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TimeSelectionScreenPreview() {
    ScrabbleTimerTheme {
        TimeSelectionScreen(
            onTimeSelected = {}
        )
    }
}