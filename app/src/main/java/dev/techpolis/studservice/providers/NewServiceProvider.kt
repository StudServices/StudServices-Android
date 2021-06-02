package dev.techpolis.studservice.providers

import dev.techpolis.studservice.data.model.DeadlineDate
import dev.techpolis.studservice.data.model.ServiceTypeEnum
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton

class NewServiceProvider @Inject constructor(){
    var title: String? = null
    var description: String? = null
    var price: Long? = null
    var tags: MutableList<String> = mutableListOf()
    var deadline: DeadlineDate? = null
    var type: ServiceTypeEnum = ServiceTypeEnum.OFFER

    fun clear() {
        title = null
        description = null
        price = null
        tags.clear()
        deadline = null
        type = ServiceTypeEnum.OFFER
    }

}