package dev.techpolis.studservice.data.model

import dev.techpolis.studservice.utils.timeToString
import java.util.*

data class DeadlineDate(val dayOfMonth: Int, val month: Int, val year: Int) {
    override fun toString(): String {
        return timeToString(toTimeMillis())
    }

    fun toTimeMillis(): Long {
        val calDate = Calendar.getInstance()
        calDate.set(year, month, dayOfMonth)
        return calDate.timeInMillis
    }
}
