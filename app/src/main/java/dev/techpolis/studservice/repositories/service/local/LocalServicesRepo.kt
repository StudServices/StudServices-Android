package dev.techpolis.studservice.repositories.service.local

import dev.techpolis.studservice.data.entities.ServiceEntity
import dev.techpolis.studservice.data.model.ServiceTypeEnum
import dev.techpolis.studservice.data.model.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

interface LocalServicesRepo {
    fun readServices(limit: Int, offset: Int): Flow<List<ServiceEntity>>
    fun readServicesByType(
        type: ServiceTypeEnum,
        limit: Int,
        offset: Int
    ): Flow<List<ServiceEntity>>

    fun readServicesByUser(userId: String, limit: Int, offset: Int): Flow<List<ServiceEntity>>
    fun readServicesByUserAndType(
        userId: String,
        type: ServiceTypeEnum,
        limit: Int,
        offset: Int
    ): Flow<List<ServiceEntity>>

    suspend fun addService(service: ServiceEntity)
    suspend fun addServices(services: List<ServiceEntity>)
    suspend fun deleteService(service: ServiceEntity)
    suspend fun updateService(newService: ServiceEntity)
}