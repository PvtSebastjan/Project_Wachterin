package com.sebastjanjernejjapelj.project_wachterin.func

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.sebastjanjernejjapelj.project_wachterin.func.data.UserDataFormat
import com.sebastjanjernejjapelj.project_wachterin.func.data.UserViewModel
import com.sebastjanjernejjapelj.project_wachterin.pages.LoginPage

@Composable
fun LoginReq(viewModel: UserViewModel, onLoginSuccess: () -> Unit) {
    // Get the user info directly from the ViewModel
    val userInfo = viewModel.userList.firstOrNull() // Assuming you want to check against the first user

    val context = LocalContext.current.applicationContext
    LoginPage(
        onLoginClick = { username, password ->
            if (userInfo?.let { checkInput(username, password, it) } == true) {
                onLoginSuccess() // Navigate to Home on successful login
                Toast.makeText(context, "Access Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Invalid Username or Password", Toast.LENGTH_SHORT).show()
            }
        }
    )
}

// Update the checkInput function to receive userInfo
private fun checkInput(username: String, password: String, userInfo: UserDataFormat): Boolean {
    return username == userInfo.userInfoName && password == userInfo.userInfoPassword
}





