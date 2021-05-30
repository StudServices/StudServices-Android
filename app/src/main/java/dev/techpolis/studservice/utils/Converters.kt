package dev.techpolis.studservice.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun timeToString(time: Long): String {
    val formatter = SimpleDateFormat("dd.MM")
    val calendar: Calendar = Calendar.getInstance()
    calendar.timeInMillis = time
    return formatter.format(calendar.time)
}