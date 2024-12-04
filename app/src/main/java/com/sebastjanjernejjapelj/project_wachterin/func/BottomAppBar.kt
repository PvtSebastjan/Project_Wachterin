package com.sebastjanjernejjapelj.project_wachterin.func

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.sebastjanjernejjapelj.project_wachterin.Screens
import com.sebastjanjernejjapelj.project_wachterin.navHost.AppNavHost

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MyBottomAppBar(settingsViewModel: SettingsViewModel) {
    val navigationController = rememberNavController()
    val context = LocalContext.current.applicationContext
    val selected = remember { mutableStateOf(Icons.Default.Home) }
    val footPrint = settingsViewModel.footPrint

    // Access the colorScheme from your theme
    val colorScheme = MaterialTheme.colorScheme

    Scaffold(
        bottomBar = {
            BottomAppBar(containerColor = colorScheme.primary) {
                IconButton(
                    onClick = {
                        selected.value = Icons.Default.Home
                        navigationController.navigate(Screens.Home.screen) {
                            popUpTo(0)
                        }
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        Icons.Default.Home, contentDescription = null, modifier = Modifier.size(26.dp),
                        tint = if (selected.value == Icons.Default.Home) colorScheme.onPrimary else colorScheme.onSurface
                    )
                }

                IconButton(
                    onClick = {
                        selected.value = Icons.Default.LocationOn
                        navigationController.navigate(Screens.FindMe.screen) {
                            popUpTo(0)
                        }
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        Icons.Default.LocationOn, contentDescription = null, modifier = Modifier.size(26.dp),
                        tint = if (selected.value == Icons.Default.LocationOn) colorScheme.onPrimary else colorScheme.onSurface
                    )
                }

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    val context = LocalContext.current
                    val iconTint = if (footPrint) colorScheme.secondary else colorScheme.error

                    FloatingActionButton(
                        onClick = {
                            if (footPrint) {
                                footPrintTracker(context)
                            } else {
                                Toast.makeText(context, "Don't worry, we respect your privacy.", Toast.LENGTH_LONG).show()
                            }
                        },
                        elevation = FloatingActionButtonDefaults.elevation(8.dp),
                        shape = CircleShape
                    ) {
                        Icon(
                            Icons.Default.Warning,
                            contentDescription = if (footPrint) "Privacy warning active" else "Privacy warning inactive",
                            tint = iconTint
                        )
                    }
                }

                IconButton(
                    onClick = {
                        selected.value = Icons.Default.Notifications
                        navigationController.navigate(Screens.Notification.screen) {
                            popUpTo(0)
                        }
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        Icons.Default.Notifications, contentDescription = null, modifier = Modifier.size(26.dp),
                        tint = if (selected.value == Icons.Default.Notifications) colorScheme.onPrimary else colorScheme.onSurface
                    )
                }

                IconButton(
                    onClick = {
                        selected.value = Icons.Default.Person
                        navigationController.navigate(Screens.User.screen) {
                            popUpTo(0)
                        }
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        Icons.Default.Person, contentDescription = null, modifier = Modifier.size(26.dp),
                        tint = if (selected.value == Icons.Default.Person) colorScheme.onPrimary else colorScheme.onSurface
                    )
                }
            }
        }
    ) { paddingValues ->
        AppNavHost(navController = navigationController, paddingValues = paddingValues, settingsViewModel=settingsViewModel)
    }
}







