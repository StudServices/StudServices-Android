package dev.techpolis.studservice.data.database

import androidx.room.*
import dev.techpolis.studservice.data.entities.ServiceEntity
import dev.techpolis.studservice.data.model.ServiceTypeEnum
import kotlinx.coroutines.flow.Flow

@Dao
interface ServiceDAO {

    @Query("SELECT * FROM ${ServiceEntity.TABLE_NAME} ORDER BY id DESC")
    fun readAllService(): Flow<List<ServiceEntity>>

    @Query("SELECT * FROM ${ServiceEntity.TABLE_NAME} WHERE ownerId=:userId")
    fun readAllServiceByUserId(userId: String): Flow<List<ServiceEntity>>

    @Query("SELECT * FROM ${ServiceEntity.TABLE_NAME} WHERE ownerId=:userId AND type=:type")
    fun readAllServiceByUserIdAndType(
        userId: String,
        type: ServiceTypeEnum
    ): Flow<List<ServiceEntity>>

    @Query("SELECT * FROM ${ServiceEntity.TABLE_NAME} WHERE type=:type")
    fun readAllServiceByType(type: ServiceTypeEnum): Flow<List<ServiceEntity>>

    @Insert(entity = ServiceEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun addService(service: ServiceEntity)

    @Insert(entity = ServiceEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun addServices(services: List<ServiceEntity>)

    @Delete(entity = ServiceEntity::class)
    suspend fun deleteService(service: ServiceEntity)

    @Update(entity = ServiceEntity::class)
    suspend fun updateService(service: ServiceEntity)
}