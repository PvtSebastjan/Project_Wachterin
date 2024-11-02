package com.sebastjanjernejjapelj.project_wachterin.func.data

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
fun getFormattedDates(): Pair<String, String> {
    val currentDate = LocalDate.now()
    val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy")

    val formattedCurrentDate = currentDate.format(formatter)
    val formattedPreviousDate = currentDate.minusDays(1).format(formatter)

    return Pair(formattedCurrentDate, formattedPreviousDate)
}

@RequiresApi(Build.VERSION_CODES.O)
fun getNotificationsList(): List<NotificationCardData> {
    val (formattedCurrentDate, formattedPreviousDate) = getFormattedDates()

    return listOf(
        NotificationCardData(
            titleOfCard = "New Message",
            dateOfCard = formattedCurrentDate,
            authorOfCard = "John Doe",
            exertOfCard = "You have received a new message in your inbox. Please check it out as soon as possible."
        ),
        NotificationCardData(
            titleOfCard = "System Update",
            dateOfCard = formattedCurrentDate,
            authorOfCard = "App Team",
            exertOfCard = "There is a new system update available. Make sure to install it to get the latest features."
                    + "May the Holy trinity guide you"
                    + "-Team Holy"
        ),
        NotificationCardData(
            titleOfCard = "Reminder",
            dateOfCard = formattedCurrentDate,
            authorOfCard = "App Team",
            exertOfCard = "Don't forget to complete your profile. This will help us serve you better."
                    + "May the Holy trinity guide you"
                    + "-Team Holy"
        ),
        NotificationCardData(
            titleOfCard = "System Update",
            dateOfCard = formattedPreviousDate,
            authorOfCard = "App Team",
            exertOfCard = "There is a new system update available. Make sure to install it to get the latest features."
                    + "May the Holy trinity guide you"
                    + "-Team Holy"
        ),
        NotificationCardData(
            titleOfCard = "Careful where you go",
            dateOfCard = formattedPreviousDate,
            authorOfCard = "App",
            exertOfCard = "The lord protects, but that doesn't mean you shouldn't use your head"
                    + "-The App"
        ),
        NotificationCardData(
            titleOfCard = "Welcome User",
            dateOfCard = formattedPreviousDate,
            authorOfCard = "App Team",
            exertOfCard = "We welcome you as a new user, and we hope you will enjoy using this app."
                    + "May the Holy trinity guide you"
                    + "-Team Holy"
        )
    )
}
