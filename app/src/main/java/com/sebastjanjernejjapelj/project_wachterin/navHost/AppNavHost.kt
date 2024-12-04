package com.sebastjanjernejjapelj.project_wachterin.navHost

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sebastjanjernejjapelj.project_wachterin.Screens
import com.sebastjanjernejjapelj.project_wachterin.pages.FindMe
import com.sebastjanjernejjapelj.project_wachterin.pages.Home
import com.sebastjanjernejjapelj.project_wachterin.pages.Notifications
import com.sebastjanjernejjapelj.project_wachterin.pages.User
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.sebastjanjernejjapelj.project_wachterin.func.SettingsViewModel
import com.sebastjanjernejjapelj.project_wachterin.func.data.UserViewModel
import com.sebastjanjernejjapelj.project_wachterin.pages.CardFull
import com.sebastjanjernejjapelj.project_wachterin.ui.theme.Project_WachterinTheme

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavHost(
    navController: NavHostController,
    paddingValues: PaddingValues,
    settingsViewModel: SettingsViewModel // Pass the SettingsViewModel as a parameter
) {
    // Wrap the content with the theme
    Project_WachterinTheme(darkTheme = settingsViewModel.isDarkTheme) {
        val userViewModel: UserViewModel = viewModel()
        val context = LocalContext.current // Retrieve the current context here

        NavHost(
            navController = navController,
            startDestination = Screens.Home.screen,
            modifier = Modifier.padding(paddingValues) // Apply padding using Modifier.padding
        ) {
            composable(Screens.Home.screen) { Home() }
            composable(Screens.FindMe.screen) { FindMe(context = context) } // Use context here
            composable(Screens.Notification.screen) {
                Notifications(navController = navController, settingsViewModel = settingsViewModel,  userViewModel= userViewModel)
            }
            composable(Screens.User.screen) {
                User(navController = navController, viewModel = userViewModel, settingsViewModel = settingsViewModel)
            }
            composable(
                route = "cardFull/{titleOfCard}/{dateOfCard}/{authorOfCard}/{exertOfCard}",
                arguments = listOf(
                    navArgument("titleOfCard") { type = NavType.StringType },
                    navArgument("dateOfCard") { type = NavType.StringType },
                    navArgument("authorOfCard") { type = NavType.StringType },
                    navArgument("exertOfCard") { type = NavType.StringType }
                )
            ) { backStackEntry ->
                CardFull(
                    titleOfCard = backStackEntry.arguments?.getString("titleOfCard") ?: "",
                    dateOfCard = backStackEntry.arguments?.getString("dateOfCard") ?: "",
                    authorOfCard = backStackEntry.arguments?.getString("authorOfCard") ?: "",
                    exertOfCard = backStackEntry.arguments?.getString("exertOfCard") ?: "",
                    navController = navController
                )
            }

        }
    }
}
