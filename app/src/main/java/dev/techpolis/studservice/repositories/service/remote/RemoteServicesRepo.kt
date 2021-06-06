package dev.techpolis.studservice.repositories.service.remote

import dev.techpolis.studservice.data.entities.ServiceEntity
import dev.techpolis.studservice.data.model.ServiceTypeEnum
import dev.techpolis.studservice.data.model.User
import kotlinx.coroutines.flow.Flow

interface RemoteServicesRepo {
    suspend fun readServices(limit: Int, offset: Int): List<ServiceEntity>
    suspend fun readServicesByType(
        type: ServiceTypeEnum,
        limit: Int,
        offset: Int
    ): List<ServiceEntity>

    suspend fun readServicesByUser(userId: String, limit: Int, offset: Int): List<ServiceEntity>
    suspend fun readServicesByUserAndType(
        userId: String,
        type: ServiceTypeEnum,
        limit: Int,
        offset: Int
    ): List<ServiceEntity>

    suspend fun addService(service: ServiceEntity)
    suspend fun addServices(services: List<ServiceEntity>)
    suspend fun deleteService(service: ServiceEntity)
    suspend fun updateService(newService: ServiceEntity)
}