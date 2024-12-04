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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.sebastjanjernejjapelj.project_wachterin.R
import com.sebastjanjernejjapelj.project_wachterin.func.SettingsViewModel
import com.sebastjanjernejjapelj.project_wachterin.func.data.UserDataFormat
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

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp),
        contentPadding = PaddingValues(bottom = 15.dp) // Add padding at the bottom to prevent cut-off
    ) {
        item {
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
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
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
        }

        item {
            Spacer(modifier = Modifier.height(10.dp))
        }

        item {
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
        }

        item {
            Spacer(modifier = Modifier.height(10.dp))
        }

        item {
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
                SectionTitle(title = "Done for today?")
                Text(
                    text = "Tap to Logout",
                    fontSize = 15.sp,
                    color = Color.Red,
                    modifier = Modifier.clickable {
                        showDialog = true
                    }
                )
            }
        }

        // Show email edit dialog
        if (showEditEmailDialog) {
            item {
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
        }

        // Show phone edit dialog
        if (showEditPhoneDialog) {
            item {
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
        }

        // Logout confirmation dialog
        if (showDialog) {
            item {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    title = { Text(text = "Exit Confirmation") },
                    text = { Text("Are you sure you want to log out and exit the app?") },
                    confirmButton = {
                        Button(onClick = {
                            Toast.makeText(context, "Logging out...", Toast.LENGTH_SHORT).show()
                            showDialog = false
                            navController.navigate("login")
                        }) {
                            Text("Yes")
                        }
                    },
                    dismissButton = {
                        Button(onClick = {
                            Toast.makeText(context, "Action cancelled", Toast.LENGTH_SHORT).show()
                            showDialog = false
                        }) {
                            Text("No")
                        }
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserPreview() {
    // Mock UserDataFormat for preview (replace with actual class)
    val mockUser = UserDataFormat(
        userInfoName = "John Doe",
        userInfoId = "12345",
        userInfoEmail = "john.doe@example.com",
        userInfoPhoneNumber = "+1234567890",
        userInfoProfilePic = R.drawable.profile_placeholder, // Ensure this image exists or use a placeholder
        userInfoPassword = "password123" // Provide a mock password value
    )

    // Create a mock UserViewModel for the preview
    val userViewModel = UserViewModel().apply {
        currentUser = mockUser
    }

    // Create a mock SettingsViewModel for the preview
    val settingsViewModel = SettingsViewModel()

    // Wrap in MaterialTheme to ensure the composable looks as it would in the app
    MaterialTheme {
        User(
            navController = rememberNavController(), // Preview doesn't use real navigation
            viewModel = userViewModel,               // Use the mock UserViewModel
            settingsViewModel = settingsViewModel    // Use the mock SettingsViewModel
        )
    }
}








