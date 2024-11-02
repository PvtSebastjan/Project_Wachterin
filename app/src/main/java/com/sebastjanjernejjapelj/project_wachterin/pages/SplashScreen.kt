package com.sebastjanjernejjapelj.project_wachterin.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sebastjanjernejjapelj.project_wachterin.R
import com.sebastjanjernejjapelj.project_wachterin.ui.theme.AppTypography
import com.sebastjanjernejjapelj.project_wachterin.ui.theme.Project_WachterinTheme
import com.sebastjanjernejjapelj.project_wachterin.ui.theme.secondaryLightMediumContrast
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sebastjanjernejjapelj.project_wachterin.navigation.Routes
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {
    LaunchedEffect(Unit) {
        delay(5000L) // Delay for 3 seconds
        // Navigate to the Login screen after the splash
        navController.navigate(Routes.LOGIN) {
            popUpTo(Routes.SPLASH) { inclusive = true } // Clear the splash screen from the backstack
        }
    }

    // Splash screen UI
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background) // Set background color
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(bottom = 20.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.projectwechtarin_logo),
                contentDescription = "Logo",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.size(90.dp)
            )
            Text(
                text = "Wachterin",
                fontSize = 70.sp,
                color = secondaryLightMediumContrast,
                style = AppTypography.displayLarge
            )
        }
        RandomSplashText((0..11).random()) // Shows a random quote
    }
}

@Composable
fun RandomSplashText(randomNum: Int) {
    val splashText = arrayOf(
        "I pray for the safety of all good people who come to Zion, even Gentiles, but we can't expect God to do all the work.",
        "Science and Religion aren't two exclusive things, and Atheism isn't the key to Science.",
        "The first gulp from the glass of natural sciences will turn you into an atheist, but at the bottom of the glass God is waiting for you.",
        "The study of God's creation is called Science...",
        "Ask, and it will be given to you; seek, and you will find; knock, and it will be opened to you.",
        "Atheists express their rage against God although in their view, He does not exist.",
        "Those who misuse the word of God for selfish gain are destined to suffer God's wrath...",
        "So many have resorted to teaching with force and threat, even those who work under our Lord...",
        "My help comes from the Lord, who made heaven and earth.",
        "Waging war upon good folk does nothing but ruin a good soul. A simple yet important fact nonetheless...",
        "You will seek me and find me, when you seek me with all your heart.",
        "To suffer without purpose is to know only pain; to suffer with meaning is to find value in the struggle."
    )

    val splashAuthor = arrayOf(
        "-Joshua Graham",
        "-Father Thomas Dawnson",
        "-Werner Heisenberg (1932)",
        "-Brother Fabius \"Cutty\" Smith",
        "-Matthew 7:7",
        "-C. S. Lewis",
        "-Sister Amelia Berg",
        "-Sister Rosalia Nikolaev",
        "-Psalm 121:2",
        "-Father Thomas Dawnstar",
        "-Jeremiah 29:13",
        "-Brother Martin \"Bartholomew\" Gordon"
    )

    // Display the random splash text
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 40.dp) // Padding from the bottom
    ) {
        Text(
            text = splashText[randomNum],
            fontSize = 16.sp,
            style = AppTypography.bodyMedium,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = splashAuthor[randomNum],
            fontSize = 14.sp,
            style = AppTypography.bodySmall,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewSplashScreen() {
    Project_WachterinTheme {
        SplashScreen(navController = rememberNavController()) // Set a fixed index for preview
    }
}


