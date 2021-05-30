package dev.techpolis.studservice.data.entities

import android.app.Service
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import dev.techpolis.studservice.data.model.ServiceTypeEnum
import java.lang.IllegalArgumentException

@Entity(tableName = ServiceEntity.TABLE_NAME)
data class ServiceEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val ownerId: Long,
    val description: String,
    val price: Int,

    @SerializedName("tags")
    @TypeConverters(ListTypeConverter::class)
    val tagList: List<String>,

    val type: ServiceTypeEnum,
    val deadlineTime: Long,

    ) {
    companion object {
        const val TABLE_NAME = "service_table"
    }
}

class ListTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun toList(json: String): List<String> =
        gson.fromJson(json, object : TypeToken<List<String>>() {}.type)

    @TypeConverter
    fun toJson(list: List<String>): String =
        gson.toJson(list)
}

class ServiceTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun toInt(type: ServiceTypeEnum): Int = type.ordinal

    @Throws(IllegalArgumentException::class)
    @TypeConverter
    fun toType(value: Int): ServiceTypeEnum =
        when (value) {
            0 -> ServiceTypeEnum.OFFER
            1 -> ServiceTypeEnum.REQUEST
            else -> throw IllegalArgumentException()
        }
}
