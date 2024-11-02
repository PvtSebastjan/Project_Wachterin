package com.sebastjanjernejjapelj.project_wachterin.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sebastjanjernejjapelj.project_wachterin.func.SettingBoxFunction
import android.widget.Toast
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.sebastjanjernejjapelj.project_wachterin.func.SettingsViewModel
import com.sebastjanjernejjapelj.project_wachterin.func.data.UserViewModel
import com.sebastjanjernejjapelj.project_wachterin.ui.theme.AppTypography



@Composable
fun User(navController: NavController, viewModel: UserViewModel, settingsViewModel: SettingsViewModel) {
    val currentUser = viewModel.currentUser
    var showDialog by remember { mutableStateOf(false) }
    var showEditEmailDialog by remember { mutableStateOf(false) }
    var showEditPhoneDialog by remember { mutableStateOf(false) }
    var newEmail by remember { mutableStateOf(currentUser.userInfoEmail) }
    var newPhone by remember { mutableStateOf(currentUser.userInfoPhoneNumber) }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        // Profile Section
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.onPrimary, RoundedCornerShape(8.dp))
                .border(
                    2.dp,
                    MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(horizontal = 50.dp, vertical = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.padding(5.dp)
            ) {
                Image(
                    painter = painterResource(currentUser.userInfoProfilePic),
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(25.dp))
                Column(
                    modifier = Modifier.align(Alignment.CenterVertically)
                ) {
                    Text(
                        text = currentUser.userInfoName,
                        fontSize = 30.sp,
                        style = AppTypography.displayMedium
                    )
                    Text(
                        text = currentUser.userInfoId,
                        fontSize = 15.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Settings Section
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    2.dp,
                    MaterialTheme.colorScheme.onSurfaceVariant,
                    shape = RoundedCornerShape(8.dp)
                )
                .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(8.dp))
                .padding(vertical = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SectionTitle(title = "Settings")
            SettingBoxFunction(settingsViewModel)
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Change Info Section
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .border(2.dp, MaterialTheme.colorScheme.onSurfaceVariant, shape = RoundedCornerShape(8.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(8.dp))
                .padding(horizontal = 50.dp, vertical = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SectionTitle(title = "Change Info?")
            Text(
                text = "Change Email: ${currentUser.userInfoEmail}",
                fontSize = 15.sp,
                modifier = Modifier.clickable { showEditEmailDialog = true }
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Change Phone: ${currentUser.userInfoPhoneNumber}",
                fontSize = 15.sp,
                modifier = Modifier.clickable { showEditPhoneDialog = true }
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Logout Section
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    2.dp,
                    MaterialTheme.colorScheme.error,
                    shape = RoundedCornerShape(8.dp)
                )
                .background(MaterialTheme.colorScheme.onError, RoundedCornerShape(8.dp))
                .padding(horizontal = 50.dp, vertical = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SectionTitle(title = "Logout?")
            Text(
                text = "Tap to Logout",
                fontSize = 15.sp,
                color = Color.Red,
                modifier = Modifier.clickable {
                    showDialog = true
                }
            )
        }

        // Show email edit dialog
        if (showEditEmailDialog) {
            AlertDialog(
                onDismissRequest = { showEditEmailDialog = false },
                title = { Text(text = "Edit Email") },
                text = {
                    Column {
                        Text("Enter new email:")
                        TextField(
                            value = newEmail,
                            onValueChange = { newEmail = it },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                },
                confirmButton = {
                    Button(onClick = {
                        viewModel.updateEmail(newEmail) // Update email in ViewModel
                        Toast.makeText(context, "Email updated to $newEmail", Toast.LENGTH_SHORT).show()
                        showEditEmailDialog = false
                    }) {
                        Text("Save")
                    }
                },
                dismissButton = {
                    Button(onClick = { showEditEmailDialog = false }) {
                        Text("Cancel")
                    }
                }
            )
        }

        // Show phone edit dialog
        if (showEditPhoneDialog) {
            AlertDialog(
                onDismissRequest = { showEditPhoneDialog = false },
                title = { Text(text = "Edit Phone Number") },
                text = {
                    Column {
                        Text("Enter new phone number:")
                        TextField(
                            value = newPhone,
                            onValueChange = { newPhone = it },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                },
                confirmButton = {
                    Button(onClick = {
                        viewModel.updatePhone(newPhone) // Update phone in ViewModel
                        Toast.makeText(context, "Phone number updated to $newPhone", Toast.LENGTH_SHORT).show()
                        showEditPhoneDialog = false
                    }) {
                        Text("Save")
                    }
                },
                dismissButton = {
                    Button(onClick = { showEditPhoneDialog = false }) {
                        Text("Cancel")
                    }
                }
            )
        }

        // Logout confirmation dialog
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text(text = "Logout Confirmation") },
                text = { Text("Are you sure you want to log out?") },
                confirmButton = {
                    Button(onClick = {
                        Toast.makeText(context, "Logging out...", Toast.LENGTH_SHORT).show()
                        showDialog = false
                        navController.navigate("login") {
                            popUpTo("home") { inclusive = true }
                        }
                    }) {
                        Text("Yes")
                    }
                },
                dismissButton = {
                    Button(onClick = {
                        Toast.makeText(context, "Logout cancelled", Toast.LENGTH_SHORT).show()
                        showDialog = false
                    }) {
                        Text("No")
                    }
                }
            )
        }
    }
}





