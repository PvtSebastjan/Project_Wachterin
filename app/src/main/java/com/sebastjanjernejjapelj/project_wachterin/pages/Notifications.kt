package com.sebastjanjernejjapelj.project_wachterin.pages

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.UserInfo
import com.sebastjanjernejjapelj.project_wachterin.func.BibleVerse
import com.sebastjanjernejjapelj.project_wachterin.func.SettingsViewModel
import com.sebastjanjernejjapelj.project_wachterin.func.data.NotificationCardData
import com.sebastjanjernejjapelj.project_wachterin.func.data.UserDataFormat
import com.sebastjanjernejjapelj.project_wachterin.func.data.UserViewModel
import com.sebastjanjernejjapelj.project_wachterin.func.data.getFormattedDates
import com.sebastjanjernejjapelj.project_wachterin.func.fetchRandomBibleVerse
import com.sebastjanjernejjapelj.project_wachterin.ui.theme.AppTypography

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Notifications(
    navController: NavController,
    settingsViewModel: SettingsViewModel,
    userViewModel: UserViewModel // Added this parameter
) {
    val notificationsList = settingsViewModel.notificationsList
    var bibleVerse by remember { mutableStateOf<BibleVerse?>(null) }
    var showNewNotificationDialog by remember { mutableStateOf(false) }

    // Fetch the Bible verse in a coroutine
    LaunchedEffect(Unit) {
        bibleVerse = fetchRandomBibleVerse()
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Row {
            Text(
                text = "Notifications",
                fontSize = 30.sp,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(15.dp)
            )

            // Button to create a new notification
            Button(
                onClick = { showNewNotificationDialog = true },
                modifier = Modifier.padding(start = 65.dp, top = 10.dp, bottom = 10.dp)
            ) {
                Text(text = "Add New Notification")
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 15.dp, vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            if (settingsViewModel.gottMitUns && bibleVerse != null) {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(2.dp, MaterialTheme.colorScheme.onSurfaceVariant, shape = RoundedCornerShape(8.dp))
                            .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(8.dp))
                            .padding(15.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        bibleVerse?.let { verse ->
                            Row(
                                modifier = Modifier,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = verse.book,
                                    style = AppTypography.displayMedium,
                                    fontSize = 25.sp
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(
                                    text = "${verse.chapter}:${verse.verse}",
                                    color = Color.Gray,
                                    style = AppTypography.bodyMedium,
                                    fontSize = 20.sp
                                )
                            }
                            Text(
                                text = verse.text,
                                style = AppTypography.bodyMedium,
                                fontSize = 15.sp
                            )
                        }
                    }
                }
            }

            items(notificationsList) { notification ->
                CardBuild(
                    notificationCardData = notification,
                    onClick = {
                        navController.navigate(
                            "cardFull/${notification.titleOfCard}/${notification.dateOfCard}/${notification.authorOfCard}/${notification.exertOfCard}"
                        )
                    }
                )
            }
        }

        if (showNewNotificationDialog) {
            NewNotificationDialog(
                viewModel = userViewModel, // Pass UserViewModel
                onDismiss = { showNewNotificationDialog = false },
                onConfirm = { newNotification ->
                    settingsViewModel.addNotification(newNotification)
                    showNewNotificationDialog = false
                }
            )
        }
    }
}




@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NewNotificationDialog(
    viewModel: UserViewModel,
    onDismiss: () -> Unit,
    onConfirm: (NotificationCardData) -> Unit
) {
    val currentUser = viewModel.currentUser // Assuming currentUser is the logged-in user
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }

    val isFormValid = title.isNotBlank() && content.isNotBlank()

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text(text = "Create New Notification") },
        text = {
            Column {
                TextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text(text = "Title") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = content,
                    onValueChange = { content = it },
                    label = { Text(text = "Content") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    val date = getFormattedDates().first // Adjusted to retrieve the current date
                    onConfirm(
                        NotificationCardData(
                            titleOfCard = title,
                            dateOfCard = date,
                            authorOfCard = currentUser.userInfoName, // Retrieve user's name
                            exertOfCard = content
                        )
                    )
                },
                enabled = isFormValid // Disable button if form is invalid
            ) {
                Text(text = "Create")
            }
        },
        dismissButton = {
            Button(onClick = { onDismiss() }) {
                Text(text = "Cancel")
            }
        }
    )
}












