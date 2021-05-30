package dev.techpolis.studservice.repositories.service.local

import dev.techpolis.studservice.data.database.ServiceDAO
import dev.techpolis.studservice.data.entities.ServiceEntity
import dev.techpolis.studservice.data.model.ServiceTypeEnum
import dev.techpolis.studservice.data.model.User
import dev.techpolis.studservice.di.scope.ActivityScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalServicesRepoImpl @Inject constructor(
    private val servicesDAO: ServiceDAO,
): LocalServicesRepo {

    override fun readServices(limit: Int, offset: Int): Flow<List<ServiceEntity>> {
        return servicesDAO.readAllService()
    }

    override fun readServicesByType(
        type: ServiceTypeEnum,
        limit: Int,
        offset: Int
    ): Flow<List<ServiceEntity>> {
        return servicesDAO.readAllServiceByType(type.ordinal)
    }

    override fun readServicesByUser(
        userId: Long,
        limit: Int,
        offset: Int
    ): Flow<List<ServiceEntity>> {
        return servicesDAO.readAllServiceByUserId(userId)
    }

    override fun readServicesByUserAndType(
        userId: Long,
        type: ServiceTypeEnum,
        limit: Int,
        offset: Int
    ): Flow<List<ServiceEntity>> {
        return servicesDAO.readAllServiceByUserIdAndType(userId, type.ordinal)
    }

    override suspend fun addService(service: ServiceEntity) {
        servicesDAO.addService(service)
    }

    override suspend fun addServices(services: List<ServiceEntity>) {
        servicesDAO.addServices(services)
    }

    override suspend fun deleteService(service: ServiceEntity) {
        servicesDAO.deleteService(service)
    }

    override suspend fun updateService(newService: ServiceEntity) {
        servicesDAO.updateService(newService)
    }
}