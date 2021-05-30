package dev.techpolis.studservice.utils

import android.util.TimeUtils
import dev.techpolis.studservice.data.entities.ServiceEntity
import dev.techpolis.studservice.data.model.ServiceTypeEnum
import java.util.*
import kotlin.random.Random

fun generateOffers(type: ServiceTypeEnum): List<ServiceEntity> {
    val res = mutableListOf<ServiceEntity>()
    val calendar: Calendar = Calendar.getInstance()
    for (i in 1..5) {
        res.add(
            ServiceEntity(
                title = randomWord(7),
                ownerId = Random.nextLong(),
                description = randomWord(20),
                price = Random.nextInt(1000),
                type = type,
                deadlineTime = calendar.timeInMillis,
                tagList = listOf(randomWord(4), randomWord(4), randomWord(4))
            )
        )
    }
    return res
}

fun generateOffers(id: Long, type: ServiceTypeEnum): List<ServiceEntity> {
    val res = mutableListOf<ServiceEntity>()
    val calendar: Calendar = Calendar.getInstance()
    for (i in 1..5) {
        res.add(
            ServiceEntity(
                title = randomWord(7),
                ownerId = id,
                description = randomWord(20),
                price = Random.nextInt(1000),
                type = type,
                deadlineTime = calendar.timeInMillis,
                tagList = listOf(randomWord(4), randomWord(4), randomWord(4))
            )
        )
    }
    return res
}

private fun randomWord(wordLength: Long): String {
    val source = "abcdefghijklmnopqrstuvwxyz"
    val list = mutableListOf<Int>()
    for (i in 0 until wordLength) {
        list.add(Random.nextInt(0, source.length))
    }
    return list.asSequence()
        .map(source::get)
        .joinToString("")
}