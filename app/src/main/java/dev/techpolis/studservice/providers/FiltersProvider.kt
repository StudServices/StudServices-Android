package dev.techpolis.studservice.providers

import dev.techpolis.studservice.data.model.ServiceTypeEnum
import javax.inject.Inject
import javax.inject.Singleton

@Singleton

class FiltersProvider @Inject constructor(){
    var tags: MutableList<String> = mutableListOf()
    var type: ServiceTypeEnum? = null
    var priceFrom: Int = Int.MIN_VALUE
    var priceTo: Int = Int.MAX_VALUE
    var title: String = ""
    var isContentVisible: Boolean = false


    fun clear() {
        tags.clear()
        type = null
        title = ""
        priceTo = Int.MAX_VALUE
        priceFrom = Int.MIN_VALUE
        isContentVisible = false
    }

}