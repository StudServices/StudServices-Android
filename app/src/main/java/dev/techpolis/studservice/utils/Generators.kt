package dev.techpolis.studservice.utils

import dev.techpolis.studservice.data.entities.ServiceEntity
import kotlin.random.Random

fun generateOffers(): List<ServiceEntity> {
    val res = mutableListOf<ServiceEntity>()
    for (i in 1..30) {
        res.add(
            ServiceEntity(
                Random.nextLong(),
                randomWord(7),
                randomWord(20),
                Random.nextInt(1000),
                if (Random.nextBoolean()) "av1" else "av2"
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