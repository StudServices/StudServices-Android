package dev.techpolis.studservice.providers

import javax.inject.Inject
import javax.inject.Singleton
import kotlin.properties.Delegates

@Singleton
class UserProvider @Inject constructor(){
    var userId by Delegates.notNull<String>()
}