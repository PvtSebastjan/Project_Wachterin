package com.sebastjanjernejjapelj.project_wachterin.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sebastjanjernejjapelj.project_wachterin.func.MyBottomAppBar
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.sebastjanjernejjapelj.project_wachterin.func.LoginReq
import com.sebastjanjernejjapelj.project_wachterin.func.SettingsViewModel
import com.sebastjanjernejjapelj.project_wachterin.func.data.UserViewModel
import com.sebastjanjernejjapelj.project_wachterin.pages.CardFull
import com.sebastjanjernejjapelj.project_wachterin.pages.SplashScreen

object Routes {
    const val SPLASH = "splash"
    const val LOGIN = "login"
    const val HOME = "home"
    const val CARD_FULL = "card_full/{title}/{date}/{author}/{excerpt}"
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavGraph(navController: NavHostController, userViewModel: UserViewModel) {
    val settingsViewModel: SettingsViewModel = viewModel()
    NavHost(navController = navController, startDestination = Routes.SPLASH) {
        composable(Routes.SPLASH) {
            SplashScreen(navController = navController)
        }

        composable(Routes.LOGIN) {
            LoginReq(viewModel = userViewModel, onLoginSuccess = {
                navController.navigate(Routes.HOME) {
                    popUpTo(Routes.LOGIN) { inclusive = true }
                }
            })
        }

        composable(Routes.HOME) {
            HomeScreen(settingsViewModel)
        }

        composable(
            Routes.CARD_FULL,
            arguments = listOf(
                navArgument("title") { type = NavType.StringType },
                navArgument("date") { type = NavType.StringType },
                navArgument("author") { type = NavType.StringType },
                navArgument("excerpt") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val title = backStackEntry.arguments?.getString("title") ?: "Unknown Title"
            val date = backStackEntry.arguments?.getString("date") ?: "Unknown Date"
            val author = backStackEntry.arguments?.getString("author") ?: "Unknown Author"
            val excerpt = backStackEntry.arguments?.getString("excerpt") ?: "No Excerpt"
            CardFull(title, date, author, excerpt, navController = navController)
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(settingsViewModel: SettingsViewModel) {
    MyBottomAppBar(settingsViewModel)
}

