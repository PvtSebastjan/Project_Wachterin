package com.sebastjanjernejjapelj.project_wachterin.func

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.material3.HorizontalDivider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.material3.Text
import com.sebastjanjernejjapelj.project_wachterin.func.data.NotificationCardData
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun LambdaMessage(
    notifications: List<NotificationCardData>,
    filterByDate: (List<NotificationCardData>, String) -> List<NotificationCardData> = { notifications, date ->
        notifications.filter { it.dateOfCard == date }
    }
) {
    // Get today's date in the same format as notifications
    val today = remember {
        LocalDate.now().format(DateTimeFormatter.ofPattern("dd MMM yyyy"))
    }

    // Filter notifications for today
    val todaysNotifications = filterByDate(notifications, today)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorScheme.tertiary, RoundedCornerShape(8.dp))
            .border(
                2.dp,
                colorScheme.onSurfaceVariant,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(15.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Incoming messages",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        if (todaysNotifications.isNotEmpty()) {
            todaysNotifications.forEach { notification ->
                Text(
                    text = "Title: ${notification.titleOfCard}\nAuthor: ${notification.authorOfCard}",
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 8.dp),
                    thickness = 1.dp,
                    color = colorScheme.onError
                )
                Spacer(modifier = Modifier.height(4.dp))
            }
            Text(
                text = "Total messages today: ${todaysNotifications.size}",
                fontSize = 14.sp,
                color = colorScheme.onError
            )
        } else {
            Text(
                text = "No new message",
                fontSize = 15.sp
            )
        }
    }
}


fun filterNotificationsByDate(
    notifications: List<NotificationCardData>,
    date: String
): List<NotificationCardData> {
    return notifications.filter { it.dateOfCard == date }
}