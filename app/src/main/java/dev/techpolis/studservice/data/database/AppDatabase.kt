package dev.techpolis.studservice.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.techpolis.studservice.data.entities.ListTypeConverter
import dev.techpolis.studservice.data.entities.ServiceEntity
import dev.techpolis.studservice.data.entities.ServiceTypeConverter

@Database(
    entities = [ServiceEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(ListTypeConverter::class, ServiceTypeConverter::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun serviceDAO(): ServiceDAO
}