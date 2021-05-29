package dev.techpolis.studservice.interactors

import android.util.Log
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import dev.techpolis.studservice.data.Resource
import dev.techpolis.studservice.data.entities.ListTypeConverter
import dev.techpolis.studservice.data.entities.ServiceEntity
import dev.techpolis.studservice.repositories.service.local.LocalServicesRepo
import dev.techpolis.studservice.repositories.service.remote.RemoteServicesRepo
import dev.techpolis.studservice.utils.generateOffers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ServiceInteractor @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher,
    private val localServicesRepo: LocalServicesRepo,
    private val remoteServicesRepo: RemoteServicesRepo,
) {

    companion object {
        const val TAG = "ServiceInteractor"
    }

    suspend fun getServices(): Resource<List<ServiceEntity>> =
        withContext(ioDispatcher) {
            try {
                var cacheServices = localServicesRepo.readServices(100, 0).firstOrNull()
                if (cacheServices.isNullOrEmpty()) {
                    val newServices = generateOffers()
                    localServicesRepo.addServices(newServices)
                    localServicesRepo.addServices(generateOffers(2))
                    cacheServices = localServicesRepo.readServices(100, 0).first()
                }
                return@withContext Resource.success(cacheServices)
            } catch (t: Throwable) {
                return@withContext Resource.error()
            }
        }

    suspend fun getUserServices(userId: Long): Resource<List<ServiceEntity>> =
        withContext(ioDispatcher) {
            try {
                var cacheServices = localServicesRepo.readServicesByUser(userId, 100, 0).firstOrNull()
                if (cacheServices.isNullOrEmpty()) {
                    val newServices = generateOffers(userId)
                    localServicesRepo.addServices(newServices)
                    localServicesRepo.addServices(generateOffers())
                    cacheServices = localServicesRepo.readServicesByUser(userId, 100, 0).first()
                }
                return@withContext Resource.success(cacheServices)
            } catch (t: Throwable) {
                return@withContext Resource.error()
            }
        }


    suspend fun addUserService(
        title: String,
        userId: Long,
        description: String,
        price: Int,
        avatarUrl: String,
        tagList: List<String>
    ): Resource<Boolean> =
        withContext(ioDispatcher) {
            try {
                localServicesRepo.addService(
                    ServiceEntity(
                        title = title,
                        ownerId = userId,
                        description = description,
                        price = price,
                        avatarUrl = avatarUrl,
                        tagList = tagList
                    )
                )
                return@withContext Resource.success(true)
            } catch (t: Throwable) {
                return@withContext Resource.error()
            }
        }

}