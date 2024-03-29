package dev.techpolis.studservice.utils

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
                title = "Some_${type.name}${Random.nextInt(1000)}",
                ownerId = randomWord(20),
                description = randomWord(20),
                price = Random.nextInt(1000),
                type = type,
                deadlineTime = calendar.timeInMillis,
                tagList = generateTags()
            )
        )
    }
    return res
}

fun generateTags(): List<String> {
    val list = mutableListOf<String>()
    for (i in 1..5) {
        list.add("Tag${i}[${randomWord(3)}]")
    }
    return list
}

fun generateOffers(id: String, type: ServiceTypeEnum): List<ServiceEntity> {
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