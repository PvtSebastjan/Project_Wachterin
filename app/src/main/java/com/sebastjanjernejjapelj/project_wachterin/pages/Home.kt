package com.sebastjanjernejjapelj.project_wachterin.pages


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sebastjanjernejjapelj.project_wachterin.R
import com.sebastjanjernejjapelj.project_wachterin.ui.theme.AppTypography
import com.sebastjanjernejjapelj.project_wachterin.ui.theme.secondaryLightMediumContrast
import com.sebastjanjernejjapelj.project_wachterin.ui.theme.tertiaryContainerDark
import com.sebastjanjernejjapelj.project_wachterin.ui.theme.tertiaryContainerDarkHighContrast


@Composable
fun Home() {
    val newMessageHasArrived by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(25.dp)
    ) {
        item {
            // Combined Logo and Brand Text
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(15.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.projectwechtarin_logo),
                    contentDescription = "Logo",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.size(60.dp) // Adjust size as needed
                )
                Text(
                    text = "Wachterin",
                    fontSize = 45.sp,
                    color = secondaryLightMediumContrast,
                    style = AppTypography.displayLarge
                )
            }
            Text(
                text = "We can't expect God to do all the work?",
                fontSize = 20.sp,
                color = secondaryLightMediumContrast
            )
        }



        // Incoming Messages Section with Background
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorScheme.tertiary,RoundedCornerShape(8.dp))
                    .border(
                        2.dp,
                        colorScheme.onSurfaceVariant,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(15.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SectionTitle(title = "Incoming messages")
                if (newMessageHasArrived) {
                    // TODO: Handle new message
                } else {
                    Text(
                        text = "No new message",
                        fontSize = 15.sp
                    )
                }
            }
        }

        // About Section with Background
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        2.dp,
                        colorScheme.onSurfaceVariant,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .background(colorScheme.surfaceVariant, RoundedCornerShape(8.dp))
                    .padding(15.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SectionTitle(title = "About")
                Text(
                    text = "God can send out a guardian angel, but it has not much use with technology. " +
                            "It is why we created this app. The purpose is simple: give your device safety measures. " +
                            "It has an ordinance sender if you ever lose it and a footprint tracker if someone " +
                            "has tampered with your device. We also possess an optional verse sender if you need " +
                            "guidance from God (You CAN turn it off, but that would be the act of a Heretic... " +
                            "And you aren't a heretic, right?)",
                    fontSize = 15.sp
                )
            }
        }

        // Contact Section with Background
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        2.dp,
                        colorScheme.onSurfaceVariant,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .background(colorScheme.surfaceVariant, RoundedCornerShape(8.dp))
                    .padding(15.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SectionTitle(title = "Contact")
                Text(
                    text = "If you are having troubles, bugs, or similar issues, send a message via email or phone.",
                    fontSize = 20.sp
                )
                ContactDetails(email = "lordhelpme.andall@gmail.com", phone = "(985) 05 467 654")
            }
        }

        // Supporters Section with Background
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        2.dp,
                        color=tertiaryContainerDark,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .background(color=tertiaryContainerDarkHighContrast, RoundedCornerShape(8.dp))
                    .padding(15.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SectionTitle(title = "Our supporters")
                Text(
                    text = "that made this possible, from beginning to end",
                    fontSize = 15.sp
                )
                SupportersList()
                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 8.dp),
                    thickness = 1.dp,
                    color = Color.Gray
                )
                Column(verticalArrangement = Arrangement.spacedBy(8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "And a special thank you for:",
                        fontSize = 30.sp,
                        style = AppTypography.displayMedium
                    )
                    Text(
                        text = "The Holy Trinity",
                        fontSize = 15.sp
                    )
                }
            }

        }


    }
}

// Helper Composables
@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        fontSize = 30.sp,
        style = AppTypography.displayMedium
    )
}

@Composable
fun ContactDetails(email: String, phone: String) {
    Column {
        Text(text = email, fontSize = 15.sp)
        Text(text = phone, fontSize = 15.sp)
    }
}

@Composable
fun SupportersList() {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        SupporterRow("Faustus_goes_aggro", "Raymond_15_abying")
        SupporterRow("Only_Dismas_is_aced", "Willibrord5i")
        SupporterRow("Luck-s-Will", "Sebastian-abaya")
        SupporterRow("Vow-from-Kolbe", "UniteTitus")
    }
}

@Composable
fun SupporterRow(supporter1: String, supporter2: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(text = supporter1, fontSize = 15.sp, modifier = Modifier.weight(1f))
        Text(text = supporter2, fontSize = 15.sp, modifier = Modifier.weight(1f))
    }
}
