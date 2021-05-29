package dev.techpolis.studservice.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.techpolis.studservice.data.entities.ListTypeConverter
import dev.techpolis.studservice.data.entities.ServiceEntity

@Database(
    entities = [ServiceEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(ListTypeConverter::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun serviceDAO(): ServiceDAO
}