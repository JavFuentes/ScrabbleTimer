package dev.javierfuentes.scrabbletimer.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.javierfuentes.scrabbletimer.R
import dev.javierfuentes.scrabbletimer.ui.theme.Green
import dev.javierfuentes.scrabbletimer.ui.theme.ScrabbleTimerTheme
import dev.javierfuentes.scrabbletimer.ui.theme.White
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    onSplashFinished: () -> Unit = {}
) {
    val scrabblesFontFamily = FontFamily(Font(R.font.scrabbles))
    
    LaunchedEffect(Unit) {
        delay(2500L) // 2.5 seconds
        onSplashFinished()
    }
    
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Green),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.splash_scrabble),
                fontSize = 64.sp,
                fontFamily = scrabblesFontFamily,
                color = White,
                textAlign = TextAlign.Center,
                letterSpacing = 4.sp
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = stringResource(R.string.splash_timer),
                fontSize = 64.sp,
                fontFamily = scrabblesFontFamily,
                color = White,
                textAlign = TextAlign.Center,
                letterSpacing = 4.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    ScrabbleTimerTheme {
        SplashScreen()
    }
}