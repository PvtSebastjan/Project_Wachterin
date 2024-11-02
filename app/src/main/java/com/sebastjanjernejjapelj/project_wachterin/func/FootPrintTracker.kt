package com.sebastjanjernejjapelj.project_wachterin.func

import android.app.usage.UsageEvents
import android.app.usage.UsageStatsManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.util.*

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun getLastUsedApp(context: Context): String {
    val usageStatsManager = context.getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager
    val currentTime = System.currentTimeMillis()

    // Get usage stats for the last day
    val usageEvents = usageStatsManager.queryEvents(currentTime - 1000 * 60 * 60 * 24, currentTime)
    val event = UsageEvents.Event()
    var lastAppPackage: String? = null
    var lastEventTime: Long = 0

    while (usageEvents.hasNextEvent()) {
        usageEvents.getNextEvent(event)

        // Skip this app's package
        if (event.packageName != context.packageName && event.eventType == UsageEvents.Event.MOVE_TO_FOREGROUND) {
            if (event.timeStamp > lastEventTime) {
                lastAppPackage = event.packageName
                lastEventTime = event.timeStamp
            }
        }
    }

    if (lastAppPackage != null) {
        // Get app name from the package name
        return try {
            val packageManager: PackageManager = context.packageManager
            val appInfo = packageManager.getApplicationInfo(lastAppPackage, 0)
            val appName = packageManager.getApplicationLabel(appInfo).toString()

            // Format the timestamp
            val date = Date(lastEventTime)
            val formatter = SimpleDateFormat("dd.MM.yyyy 'at' HH:mm", Locale.getDefault())

            "App $appName was used on ${formatter.format(date)}"
        } catch (e: PackageManager.NameNotFoundException) {
            "Unknown App was used."
        }
    }

    return "No app usage found."
}


fun FootPrintTracker(context: Context, footPrint: Boolean) {
    if (footPrint) {
        val usageMessage = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getLastUsedApp(context)
        } else {
            "Your device does not support this feature."
        }

        Toast.makeText(context, usageMessage, Toast.LENGTH_LONG).show()
    } else {
        // If footPrint is false, show this message
        Toast.makeText(context, "Don't worry, we respect your privacy.", Toast.LENGTH_LONG).show()
    }
}

