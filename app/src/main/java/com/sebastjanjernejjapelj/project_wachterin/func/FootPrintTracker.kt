package com.sebastjanjernejjapelj.project_wachterin.func

import android.annotation.SuppressLint
import android.app.usage.UsageEvents
import android.app.usage.UsageStatsManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast
import com.sebastjanjernejjapelj.project_wachterin.MainActivity
import java.text.SimpleDateFormat
import java.util.*

fun getLastUsedApp(context: Context): String {
    val usageStatsManager = context.getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager
    val currentTime = System.currentTimeMillis()

    val usageEvents = usageStatsManager.queryEvents(currentTime - 1000 * 60 * 60 * 24, currentTime)
    val event = UsageEvents.Event()
    var lastAppPackage: String? = null
    var lastEventTime: Long = 0

    while (usageEvents.hasNextEvent()) {
        usageEvents.getNextEvent(event)

        if (event.packageName != context.packageName && event.eventType == UsageEvents.Event.MOVE_TO_FOREGROUND) {
            if (event.timeStamp > lastEventTime) {
                lastAppPackage = event.packageName
                lastEventTime = event.timeStamp
            }
        }
    }

    return if (lastAppPackage != null) {
        try {
            val packageManager = context.packageManager
            val appInfo = packageManager.getApplicationInfo(lastAppPackage, 0)
            val appName = packageManager.getApplicationLabel(appInfo).toString()
            "App $appName was last used on ${formatTimestamp(lastEventTime)}"
        } catch (e: PackageManager.NameNotFoundException) {
            "An unknown app was last used."
        }
    } else {
        "No recent app usage detected."
    }
}

@SuppressLint("ObsoleteSdkInt")
fun footPrintTracker(context: Context) {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
        Toast.makeText(context, "This feature is not supported on your device.", Toast.LENGTH_LONG).show()
        return
    }

    val activity = context as? MainActivity ?: return
    if (!isPermissionGranted(context)) {
        activity.requestUsageStatsPermission()
        return
    }

    try {
        val usageMessage = getLastUsedApp(context)
        Toast.makeText(context, usageMessage, Toast.LENGTH_LONG).show()
    } catch (e: SecurityException) {
        Toast.makeText(context, "Usage stats permission not granted.", Toast.LENGTH_LONG).show()
    } catch (e: Exception) {
        Toast.makeText(context, "Unable to fetch usage stats. Please try again.", Toast.LENGTH_LONG).show()
    }
}

private fun isPermissionGranted(context: Context): Boolean {
    val activity = context as? MainActivity ?: return false
    return activity.hasUsageStatsPermission()
}

private fun formatTimestamp(timestamp: Long): String {
    val date = Date(timestamp)
    val formatter = SimpleDateFormat("dd.MM.yyyy 'at' HH:mm", Locale.getDefault())
    return formatter.format(date)
}




