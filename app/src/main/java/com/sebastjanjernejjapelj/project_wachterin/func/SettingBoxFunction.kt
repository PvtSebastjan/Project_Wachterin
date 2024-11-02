package com.sebastjanjernejjapelj.project_wachterin.func

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel

class SettingsViewModel : ViewModel() {
    var gottMitUns by mutableStateOf(true)
    var footPrint by mutableStateOf(true)
    var lodMode by mutableStateOf(false)
}

@Composable
fun SettingBoxFunction(settingsViewModel: SettingsViewModel) {
    val settingsList = listOf("Light/Dark mode", "Footprint function", "Gott mit uns?")
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
                    "Light/Dark mode" -> settingsViewModel.lodMode
                    "Footprint function" -> settingsViewModel.footPrint
                    "Gott mit uns?" -> settingsViewModel.gottMitUns
                    else -> false
                }

                Checkbox(
                    checked = isChecked,
                    onCheckedChange = { checkedStatus ->
                        when (settingName) {
                            "Light/Dark mode" -> settingsViewModel.lodMode = checkedStatus
                            "Footprint function" -> settingsViewModel.footPrint = checkedStatus
                            "Gott mit uns?" -> settingsViewModel.gottMitUns = checkedStatus
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


