package com.sebastjanjernejjapelj.project_wachterin.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sebastjanjernejjapelj.project_wachterin.func.data.NotificationCardData
import com.sebastjanjernejjapelj.project_wachterin.ui.theme.AppTypography

@Composable
fun CardBuild(
    notificationCardData: NotificationCardData,
    excerptMaxLength: Int = 100,
    onClick: () -> Unit // Add onClick paramet
// er
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 15.dp, vertical = 10.dp)
            .border(
                2.dp,
                MaterialTheme.colorScheme.onSurfaceVariant,
                shape = RoundedCornerShape(8.dp)
            )
            .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(8.dp))
            .fillMaxWidth()
            .clickable { onClick() }, // Trigger the onClick callback when the card is clicked
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
    ) {
        Column(
            Modifier
                .padding(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = notificationCardData.titleOfCard,
                    style = AppTypography.displayMedium,
                    fontSize = 15.sp
                )

                Text(
                    text = notificationCardData.dateOfCard,
                    color = Color.Gray,
                    style = AppTypography.bodyMedium,
                    fontSize = 12.sp
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "By: ${notificationCardData.authorOfCard}",
                style = AppTypography.bodyMedium,
                fontSize = 12.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            val excerpt = if (notificationCardData.exertOfCard.length > excerptMaxLength) {
                notificationCardData.exertOfCard.take(excerptMaxLength) + "..."
            } else {
                notificationCardData.exertOfCard
            }

            Text(
                text = excerpt,
                style = AppTypography.bodySmall,
                fontSize = 12.sp
            )
        }
    }
}


