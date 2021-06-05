package dev.techpolis.studservice.interactors

import android.util.Log
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import dev.techpolis.studservice.data.Resource
import dev.techpolis.studservice.data.entities.ListTypeConverter
import dev.techpolis.studservice.data.entities.ServiceEntity
import dev.techpolis.studservice.data.model.ServiceTypeEnum
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

    suspend fun getServices(
        type: ServiceTypeEnum
    ): Resource<List<ServiceEntity>> =
        withContext(ioDispatcher) {
            try {
                val cacheServices = localServicesRepo.readServicesByType(type, 100, 0).firstOrNull()
                if (cacheServices.isNullOrEmpty()) {
                    val newServices = mutableListOf<ServiceEntity>()
                    newServices.addAll(generateOffers(ServiceTypeEnum.OFFER))
                    newServices.addAll(generateOffers(ServiceTypeEnum.REQUEST))
                    localServicesRepo.addServices(newServices)
                    Log.e(
                        TAG,
                        newServices.joinToString(separator = "\n", prefix = "NEW SERVICES:\n")
                    )
                    return@withContext getServices(type)
                }
                return@withContext Resource.success(cacheServices)
            } catch (t: Throwable) {
                return@withContext Resource.error()
            }
        }

    suspend fun getUserServices(
        userId: String,
        type: ServiceTypeEnum
    ): Resource<List<ServiceEntity>> =
        withContext(ioDispatcher) {
            try {
                val cacheServices =
                    localServicesRepo.readServicesByUserAndType(userId, type, 100, 0).firstOrNull()
                return@withContext Resource.success(cacheServices ?: listOf())
            } catch (t: Throwable) {
                return@withContext Resource.error()
            }
        }

    suspend fun addUserService(
        title: String,
        userId: String,
        description: String,
        price: Int,
        tagList: List<String>,
        type: ServiceTypeEnum,
        deadlineTime: Long
    ): Resource<Boolean> =
        withContext(ioDispatcher) {
            try {
                localServicesRepo.addService(
                    ServiceEntity(
                        title = title,
                        ownerId = userId,
                        description = description,
                        price = price,
                        type = type,
                        deadlineTime = deadlineTime,
                        tagList = tagList
                    )
                )
                return@withContext Resource.success(true)
            } catch (t: Throwable) {
                return@withContext Resource.error()
            }
        }

}