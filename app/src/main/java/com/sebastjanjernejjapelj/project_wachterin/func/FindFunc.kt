package com.sebastjanjernejjapelj.project_wachterin.func

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject

data class LocationData(val latitude: Double, val longitude: Double)

data class IpInfo(
    val ip: String,
    val city: String,
    val region: String,
    val country: String,
    val postal: String
)
suspend fun getIpInfo(): IpInfo? {
    val client = OkHttpClient()
    val request = Request.Builder()
        .url("https://ipinfo.io/86.61.30.27?token=cc569fd1d5f933")
        .build()

    return withContext(Dispatchers.IO) {
        try {
            val response = client.newCall(request).execute()
            response.body?.string()?.let { responseBody ->
                val json = JSONObject(responseBody)
                IpInfo(
                    ip = json.getString("ip"),
                    city = json.getString("city"),
                    region = json.getString("region"),
                    country = json.getString("country"),
                    postal = json.getString("postal")
                )
            }
        } catch (e: Exception) {
            null
        }
    }
}
class FindFuncPosition(context: Context) {
    private var fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    @SuppressLint("MissingPermission") // Make sure you handle permission checks outside
    suspend fun getCurrentLocation(): LocationData? {
        val location = try {
            fusedLocationClient.lastLocation.await()
        } catch (e: Exception) {
            null
        }
        return location?.let {
            LocationData(it.latitude, it.longitude)
        }
    }
}

@Composable
fun rememberFindFuncPositionData(context: Context): FindFuncPosition {
    return remember {
        FindFuncPosition(context)
    }
}

@Composable
fun FindMeLocation(
    findFuncPosition: FindFuncPosition,
    onLocationReceived: (LocationData) -> Unit
) {
    var locationData by remember { mutableStateOf<LocationData?>(null) }

    LaunchedEffect(Unit) { // Using Unit ensures this runs only once
        locationData = findFuncPosition.getCurrentLocation()
        locationData?.let { onLocationReceived(it) }
    }
}
@Composable
fun RequestPermissions(context: Context, onGranted: () -> Unit) {
    val permissions = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissionsMap ->
        if (permissionsMap.values.all { it }) {
            onGranted()
        } else {
            Toast.makeText(context, "Permissions are required to access location.", Toast.LENGTH_LONG).show()
        }
    }

    LaunchedEffect(Unit) {
        launcher.launch(permissions)
    }
}
