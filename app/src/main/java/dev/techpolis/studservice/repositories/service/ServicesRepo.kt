package dev.techpolis.studservice.repositories.service

import dev.techpolis.studservice.data.entities.ServiceEntity
import kotlinx.coroutines.flow.Flow

interface ServicesRepo {

    fun readServices(): Flow<List<ServiceEntity>>

}