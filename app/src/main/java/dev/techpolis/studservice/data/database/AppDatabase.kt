package dev.techpolis.studservice.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.techpolis.studservice.data.entities.ServiceEntity

@Database(
    entities = [ServiceEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {

    abstract fun serviceDAO(): ServiceDAO
}