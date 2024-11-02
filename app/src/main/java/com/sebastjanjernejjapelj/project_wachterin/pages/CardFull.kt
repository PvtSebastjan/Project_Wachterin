package com.sebastjanjernejjapelj.project_wachterin.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.sebastjanjernejjapelj.project_wachterin.ui.theme.AppTypography
import com.sebastjanjernejjapelj.project_wachterin.ui.theme.Project_WachterinTheme

@Composable
fun CardFull(titleOfCard: String, dateOfCard: String, authorOfCard: String, exertOfCard: String, navController: NavController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = colorScheme.background // Set background color for the entire page
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Clear Icon at the top-left
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                IconButton(
                    onClick = {
                        navController.popBackStack() // Go back to the previous screen
                    },
                    modifier = Modifier.align(Alignment.CenterVertically)
                ) {
                    Icon(
                        Icons.Default.Clear,
                        contentDescription = "Go back",
                        modifier = Modifier.size(26.dp),
                        tint = colorScheme.onSurface
                    )
                }
            }

            // Card Title and Date
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorScheme.primary, RoundedCornerShape(8.dp))
                    .padding(20.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween, // Space the title and date apart
                    verticalAlignment = Alignment.CenterVertically // Align date and title on the same vertical line
                ) {
                    Text(
                        text = titleOfCard,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        style = AppTypography.displayMedium,
                        color = colorScheme.onPrimary // Text color for better contrast
                    )

                    Text(
                        text = dateOfCard,
                        fontSize = 14.sp,
                        color = colorScheme.onPrimary,
                        style = AppTypography.bodyLarge
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Author Section
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp)
            ) {
                Text(
                    text = "By: ",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    style = AppTypography.bodyLarge,
                    color = colorScheme.onBackground
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = authorOfCard,
                    fontSize = 16.sp,
                    fontStyle = FontStyle.Italic,
                    style = AppTypography.bodyLarge,
                    color = colorScheme.onBackground
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Divider
            HorizontalDivider(
                modifier = Modifier.padding(vertical = 8.dp),
                thickness = 1.dp,
                color = Color.Gray
            )

            // Excerpt Section
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .background(colorScheme.primary, RoundedCornerShape(8.dp))
                    .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp))
                    .padding(16.dp)
            ) {
                Text(
                    text = exertOfCard,
                    fontSize = 16.sp,
                    style = AppTypography.bodyMedium,
                    color = colorScheme.onPrimary // Ensure good contrast for the excerpt text
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewCardFull() {
    val mockNavController = rememberNavController()
    Project_WachterinTheme {
        CardFull(
            titleOfCard = "Sample Title",
            dateOfCard = "22.7.2014",
            authorOfCard = "Sample Author",
            exertOfCard = "This is a sample excerpt to show how the content of the card looks with some placeholder text.",
            navController = mockNavController
        )
    }
}




