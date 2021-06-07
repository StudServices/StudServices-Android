package dev.techpolis.studservice.repositories.service.remote
import android.util.Log
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dev.techpolis.studservice.data.entities.ServiceEntity
import dev.techpolis.studservice.data.model.ServiceTypeEnum
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.resumeWithException


@ExperimentalCoroutinesApi
@Singleton
class RemoteServicesRepoImpl @Inject constructor() : RemoteServicesRepo {

    private val db = Firebase.firestore
    private val database = Firebase.database.reference
    private val serviceCollection = db.collection(Collections.SERVICES.collectionName)

    companion object {
        const val TAG = "RemoteServicesRepoImpl"
    }

    override suspend fun readServices(limit: Int, offset: Int): List<ServiceEntity>  =
//        suspendCancellableCoroutine { coroutine ->
//            database.child(Collections.SERVICES.collectionName).get()
//                .addOnSuccessListener { services ->
//                    coroutine.resume((services.value as List<*>).map { it.toObject(ServiceEntity::class.java) }) {
//                        coroutine.resumeWithException(it)
//                    }
//                }
//                .addOnFailureListener {
//                    coroutine.resumeWithException(it)
//                }
//        }
        suspendCancellableCoroutine { coroutine->
            database.child(Collections.SERVICES.collectionName).get()
                .addOnSuccessListener {
                    coroutine.resume(((it.value as HashMap<String, ServiceEntity>).values.toList()), null)
                }
        }

    override suspend fun readServicesByType(
        type: ServiceTypeEnum,
        limit: Int,
        offset: Int
    ): List<ServiceEntity>
    {
        Log.e("RepoIMPLLLL", "readServicesByType")
       return suspendCancellableCoroutine { coroutine->
            database.child(Collections.SERVICES.collectionName).get()
                .addOnSuccessListener {services ->
                    coroutine.resume(((services.value as HashMap<String, ServiceEntity>).values.toList().filter { it.type == type }), null)
                }.addOnFailureListener {
                    coroutine.resumeWithException(it)
                }
        }
    }

    override suspend fun readServicesByUser(
        userId: String,
        limit: Int,
        offset: Int
    ): List<ServiceEntity> = emptyList()

    override suspend fun readServicesByUserAndType(
        userId: String,
        type: ServiceTypeEnum,
        limit: Int,
        offset: Int
    ): List<ServiceEntity> =
        suspendCancellableCoroutine { coroutine->
            database.child(Collections.SERVICES.collectionName).get()
                .addOnSuccessListener {services ->
                    coroutine.resume((((services.value as HashMap<String, ServiceEntity>).values).toList().filter { it.type == type }), null)

                }
        }

    override suspend fun addServices(services: List<ServiceEntity>) {
        services.forEach { addService(it) }
    }

    override suspend fun addService(service: ServiceEntity) {
        serviceCollection.document(service.id.toString())
            .set(service)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added: $documentReference")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
        val firebaseId = database.push().key
        service.firebaseId = firebaseId
        database.child(Collections.SERVICES.collectionName).child(firebaseId!!).setValue(service)
    }

    override suspend fun deleteService(service: ServiceEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun updateService(newService: ServiceEntity) {
        TODO("Not yet implemented")
    }

//    private fun ServiceEntity.toMap(): Map<String, Any> = mapOf(
//        "id" to id,
//        "title" to title,
//        "ownerId" to ownerId,
//        "description" to description,
//        "price" to price,
//        "tagList" to tagList,
//        "type" to type.ordinal,
//        "deadlineTime" to deadlineTime,
//    )
//
//    private fun Map<String, Any>.toModel(): ServiceEntity = ServiceEntity(
//        id = this["id"] as String,
//        title = this["title"] as,
//        ownerId = this["ownerId"] as,
//        description = this["description"] as,
//        price = this["price"] as,
//        tagList = this["tagList"] as,
//        type = this["type"] as,
//        deadlineTime = this["deadlineTime"] as
//    )

}
