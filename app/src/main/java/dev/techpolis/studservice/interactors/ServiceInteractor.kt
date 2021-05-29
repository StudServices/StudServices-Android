package dev.techpolis.studservice.interactors

import dev.techpolis.studservice.data.Resource
import dev.techpolis.studservice.data.entities.ServiceEntity
import dev.techpolis.studservice.repositories.service.ServicesRepo
import dev.techpolis.studservice.utils.generateOffers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ServiceInteractor @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher,
   // private val servicesRepo: ServicesRepo,
) {

    suspend fun getServices(): Resource<List<ServiceEntity>> =
        withContext(ioDispatcher) {
            try {
                return@withContext Resource.success(generateOffers())
            } catch (t: Throwable) {
                return@withContext Resource.error()
            }
        }

}