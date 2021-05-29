package dev.techpolis.studservice.repositories.service.local

import dev.techpolis.studservice.data.entities.ServiceEntity
import dev.techpolis.studservice.data.model.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

interface LocalServicesRepo {
    fun readServices(limit: Int, offset: Int): Flow<List<ServiceEntity>>
    fun readServicesByUser(user: User, limit: Int, offset: Int): Flow<List<ServiceEntity>>
    suspend fun addService(service: ServiceEntity)
    suspend fun deleteService(service: ServiceEntity)
    suspend fun updateService(newService: ServiceEntity)
}