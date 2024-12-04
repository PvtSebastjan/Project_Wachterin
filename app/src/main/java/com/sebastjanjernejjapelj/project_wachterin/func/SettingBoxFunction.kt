package com.sebastjanjernejjapelj.project_wachterin.func

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.sebastjanjernejjapelj.project_wachterin.func.data.NotificationCardData
import com.sebastjanjernejjapelj.project_wachterin.func.data.getNotificationsList

@SuppressLint("NewApi")
class SettingsViewModel : ViewModel() {
    // Public state variables with private setters
    var gottMitUns by mutableStateOf(true)
    var footPrint by mutableStateOf(true)
    var isDarkTheme by mutableStateOf(false)

    // Notifications list
    private val _notificationsList = mutableStateListOf<NotificationCardData>()
    var notificationsList: SnapshotStateList<NotificationCardData> = _notificationsList

    init {
        // Pre-populate the list with initial notifications if needed
        _notificationsList.addAll(getNotificationsList())
    }

    // Notifications methods
    fun addNotification(notification: NotificationCardData) {
        if (notification.titleOfCard.isNotBlank() && notification.exertOfCard.isNotBlank()) {
            _notificationsList.add(0, notification) // Add to the top of the list
        } else {
            // Handle invalid notifications
            println("Invalid notification: $notification")
        }
    }

}


@Composable
fun SettingBoxFunction(settingsViewModel: SettingsViewModel) {
    val settingsList = listOf("Footprint function", "Gott mit uns?", "Light/Dark theme")
    val context = LocalContext.current

    Column(
        modifier = Modifier.padding(start = 20.dp)
    ) {
        settingsList.forEach { settingName ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                val isChecked = when (settingName) {
                    "Footprint function" -> settingsViewModel.footPrint
                    "Gott mit uns?" -> settingsViewModel.gottMitUns
                    "Light/Dark theme" -> settingsViewModel.isDarkTheme
                    else -> false
                }

                Checkbox(
                    checked = isChecked,
                    onCheckedChange = { checkedStatus ->
                        when (settingName) {
                            "Footprint function" -> settingsViewModel.footPrint = checkedStatus
                            "Gott mit uns?" -> settingsViewModel.gottMitUns = checkedStatus
                            "Light/Dark theme" -> settingsViewModel.isDarkTheme = checkedStatus
                        }
                        Toast.makeText(context, "$settingName: $checkedStatus", Toast.LENGTH_SHORT).show()
                    }
                )

                Spacer(modifier = Modifier.width(5.dp))

                Text(
                    text = settingName,
                    fontSize = 20.sp,
                )
            }
        }
    }
}



