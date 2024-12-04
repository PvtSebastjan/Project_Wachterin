package com.sebastjanjernejjapelj.project_wachterin.func.data

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.sebastjanjernejjapelj.project_wachterin.R

class UserViewModel : ViewModel() {
    // List of users (initialized with multiple users for selection)
    var userList by mutableStateOf(listOf(
        UserDataFormat(
            userInfoName = "admin",
            userInfoPassword = "4dminru1ez",
            userInfoProfilePic = R.drawable.projectwechtarin_logo,
            userInfoId = "#117524",
            userInfoEmail = "cutty.admin.net",
            userInfoPhoneNumber = "505-231-556"
        )
    ))

    // State to keep track of the currently selected user (default to the first user)
    var currentUser by mutableStateOf(userList[0])


    // Function to update the email of the current user
    fun updateEmail(newEmail: String) {
        currentUser = currentUser.copy(userInfoEmail = newEmail)
        updateUserInList(currentUser)
    }

    // Function to update the phone number of the current user
    fun updatePhone(newPhone: String) {
        currentUser = currentUser.copy(userInfoPhoneNumber = newPhone)
        updateUserInList(currentUser)
    }

    // Private helper function to update the user in the list
    private fun updateUserInList(updatedUser: UserDataFormat) {
        userList = userList.map {
            if (it.userInfoId == updatedUser.userInfoId) updatedUser else it
        }
    }
}




