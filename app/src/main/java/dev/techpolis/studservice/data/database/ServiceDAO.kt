package dev.techpolis.studservice.data.database

import androidx.room.*
import dev.techpolis.studservice.data.entities.ServiceEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ServiceDAO {

    @Query("SELECT * FROM ${ServiceEntity.TABLE_NAME} ORDER BY id DESC")
    fun readAllService(): Flow<List<ServiceEntity>>

    @Query("SELECT * FROM ${ServiceEntity.TABLE_NAME} WHERE ownerId=:userId")
    fun readAllServiceByUserId(userId: Long): Flow<List<ServiceEntity>>

    @Insert(entity = ServiceEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun addService(service: ServiceEntity)

    @Insert(entity = ServiceEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun addServices(services: List<ServiceEntity>)

    @Delete(entity = ServiceEntity::class)
    suspend fun deleteService(service: ServiceEntity)

    @Update(entity = ServiceEntity::class)
    suspend fun updateService(service: ServiceEntity)
}