package dev.javierfuentes.scrabbletimer.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.javierfuentes.scrabbletimer.R
import dev.javierfuentes.scrabbletimer.data.TimeOption
import dev.javierfuentes.scrabbletimer.ui.components.TimeOptionCard
import dev.javierfuentes.scrabbletimer.ui.theme.ScrabbleTimerTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimeSelectionScreen(
    onTimeSelected: (TimeOption) -> Unit,
    modifier: Modifier = Modifier
) {
    val timeOptions = TimeOption.getTimeOptions()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.app_name),
                        fontWeight = FontWeight.Bold
                    )
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.time_selection_subtitle),
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(bottom = 24.dp)
            )
            
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(timeOptions) { timeOption ->
                    TimeOptionCard(
                        timeOption = timeOption,
                        onTimeSelected = onTimeSelected
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