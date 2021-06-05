package dev.techpolis.studservice.repositories.service.remote

import dev.techpolis.studservice.data.entities.ServiceEntity
import dev.techpolis.studservice.data.model.User
import kotlinx.coroutines.flow.Flow

interface RemoteServicesRepo {
    fun readServices(limit: Int, offset: Int): Flow<List<ServiceEntity>>
    fun readServicesByUser(userId: String, limit: Int, offset: Int): Flow<List<ServiceEntity>>
    suspend fun addService(service: ServiceEntity)
    suspend fun deleteService(service: ServiceEntity)
    suspend fun updateService(newService: ServiceEntity)
}