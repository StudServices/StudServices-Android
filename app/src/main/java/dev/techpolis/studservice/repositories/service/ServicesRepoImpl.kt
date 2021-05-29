package dev.techpolis.studservice.repositories.service

import dev.techpolis.studservice.data.database.ServiceDAO
import dev.techpolis.studservice.data.entities.ServiceEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ServicesRepoImpl @Inject constructor(
    private val servicesDAO: ServiceDAO,
): ServicesRepo {
    override fun readServices(): Flow<List<ServiceEntity>> {
        return emptyFlow()
    }
}