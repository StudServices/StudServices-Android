package dev.techpolis.studservice.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = ServiceEntity.TABLE_NAME)
data class ServiceEntity(
    @PrimaryKey
    val id: Long,
) {
    companion object {
        const val TABLE_NAME = "service_table"
    }
}
