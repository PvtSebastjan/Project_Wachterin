package com.sebastjanjernejjapelj.project_wachterin.pages

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sebastjanjernejjapelj.project_wachterin.func.IpInfo
import com.sebastjanjernejjapelj.project_wachterin.func.LocationData
import com.sebastjanjernejjapelj.project_wachterin.func.getIpInfo
import com.sebastjanjernejjapelj.project_wachterin.func.rememberFindFuncPositionData
import com.sebastjanjernejjapelj.project_wachterin.ui.theme.AppTypography
import com.sebastjanjernejjapelj.project_wachterin.ui.theme.Project_WachterinTheme
import com.sebastjanjernejjapelj.project_wachterin.ui.theme.secondaryLightMediumContrast
import kotlinx.coroutines.launch

@Composable
fun FindMe(context: Context) {
    val findFuncPositionData = rememberFindFuncPositionData(context)
    var locationData by remember { mutableStateOf<LocationData?>(null) }
    var ipInfo by remember { mutableStateOf<IpInfo?>(null) }

    // Create a coroutine scope to launch coroutines outside of composable functions
    val coroutineScope = rememberCoroutineScope()

    // Initial fetch when the composable is first launched
    LaunchedEffect(Unit) {
        locationData = findFuncPositionData.getCurrentLocation()

        // Fetch IP info asynchronously
        coroutineScope.launch {
            ipInfo = getIpInfo() // Assuming getIpInfo() is implemented to fetch IP data
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
                Text(
                    text = "Find Me",
                    fontSize = 30.sp,
                    color = secondaryLightMediumContrast,
                    style = AppTypography.displayLarge
                )


            Spacer(modifier = Modifier.height(5.dp))
        }
        item {
            HorizontalDivider(
                modifier = Modifier.padding(vertical = 8.dp),
                thickness = 1.dp,
                color = Color.Gray
            )
        }
        item {
            // Current Address section
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .background(MaterialTheme.colorScheme.primary, RoundedCornerShape(8.dp))
                    .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Current Address:",
                        fontSize = 20.sp
                    )
                    if (ipInfo != null) {
                        Text(text = "IP: ${ipInfo?.ip}")
                    } else {
                        Text(text = "Fetching IP address...")
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))
        }


        item {
            // Current Coordinates section
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .background(MaterialTheme.colorScheme.primary, RoundedCornerShape(8.dp))
                    .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Info on the Coordinates:",
                        fontSize = 20.sp
                    )
                    if (ipInfo != null) {
                        Text(text = "City: ${ipInfo?.city}")
                        Text(text = "Region: ${ipInfo?.region}")
                        Text(text = "Country: ${ipInfo?.country}")
                        Text(text = "Postal: ${ipInfo?.postal}")
                    } else {
                        Text(text = "Fetching IP info...")
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))
        }

        item {
            // Display the location data or a fetching message
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .background(MaterialTheme.colorScheme.primary, RoundedCornerShape(8.dp))
                    .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Current Coordinates:",
                        fontSize = 20.sp
                    )
                    if (locationData != null) {
                        Text(
                            text = "Latitude: ${locationData?.latitude}",
                            fontSize = 16.sp
                        )
                        Text(
                            text = "Longitude: ${locationData?.longitude}",
                            fontSize = 16.sp
                        )
                    } else {
                        Text(text = "Fetching location...")
                    }
                }
            }

            Spacer(modifier = Modifier.height(15.dp))
        }

        item {
            // Refresh Button to fetch the location again
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Button(onClick = {
                    // Fetch location again on button click using a coroutine scope
                    coroutineScope.launch {
                        locationData = findFuncPositionData.getCurrentLocation()
                    }
                }) {
                    Text(
                        text = "Refresh",
                        fontSize = 20.sp
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFindMe() {
    val context = LocalContext.current
    Project_WachterinTheme {
        FindMe(context = context)
    }
}






