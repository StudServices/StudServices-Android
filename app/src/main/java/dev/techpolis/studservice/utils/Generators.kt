package dev.techpolis.studservice.utils

import dev.techpolis.studservice.data.entities.ServiceEntity
import kotlin.random.Random

fun generateOffers(): List<ServiceEntity> {
    val res = mutableListOf<ServiceEntity>()
    for (i in 1..30) {
        res.add(
            ServiceEntity(
                title = randomWord(7),
                ownerId = Random.nextLong(),
                description = randomWord(20),
                price = Random.nextInt(1000),
                avatarUrl = if (Random.nextBoolean()) "av1" else "av2",
                tagList = listOf(randomWord(4), randomWord(4), randomWord(4))
            )
        )
    }
    return res
}

fun generateOffers(id: Long): List<ServiceEntity> {
    val res = mutableListOf<ServiceEntity>()
    for (i in 1..30) {
        res.add(
            ServiceEntity(
                title = randomWord(7),
                ownerId = id,
                description = randomWord(20),
                price = Random.nextInt(1000),
                avatarUrl = if (Random.nextBoolean()) "av1" else "av2",
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