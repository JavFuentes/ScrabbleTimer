package dev.javierfuentes.scrabbletimer.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dev.javierfuentes.scrabbletimer.data.TimeOption
import dev.javierfuentes.scrabbletimer.ui.screens.TimeSelectionScreen
import dev.javierfuentes.scrabbletimer.ui.screens.TimerScreen

object Routes {
    const val TIME_SELECTION = "time_selection"
    const val TIMER = "timer/{minutes}"
    
    fun createTimerRoute(minutes: Int): String = "timer/$minutes"
}

@Composable
fun ScrabbleNavigation(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Routes.TIME_SELECTION
    ) {
        composable(Routes.TIME_SELECTION) {
            TimeSelectionScreen(
                onTimeSelected = { timeOption ->
                    navController.navigate(Routes.createTimerRoute(timeOption.minutes))
                }
            )
        }
        
        composable(
            route = Routes.TIMER,
            arguments = listOf(
                navArgument("minutes") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val minutes = backStackEntry.arguments?.getInt("minutes") ?: 1
            TimerScreen(selectedMinutes = minutes)
        }
    }
}