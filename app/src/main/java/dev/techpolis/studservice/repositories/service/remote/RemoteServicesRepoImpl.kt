package dev.techpolis.studservice.repositories.service.remote

import dev.techpolis.studservice.data.database.ServiceDAO
import dev.techpolis.studservice.data.entities.ServiceEntity
import dev.techpolis.studservice.data.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteServicesRepoImpl @Inject constructor(
    private val servicesDAO: ServiceDAO,
) : RemoteServicesRepo {

    override fun readServices(limit: Int, offset: Int): Flow<List<ServiceEntity>> {
        return emptyFlow()
    }

    override fun readServicesByUser(
        userId: Long,
        limit: Int,
        offset: Int
    ): Flow<List<ServiceEntity>> {
        TODO("Not yet implemented")
    }

    override suspend fun addService(service: ServiceEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteService(service: ServiceEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun updateService(newService: ServiceEntity) {
        TODO("Not yet implemented")
    }
}