package dev.techpolis.studservice.providers

import dev.techpolis.studservice.data.entities.ServiceEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ServiceInfoProvider @Inject constructor() {
    lateinit var service: ServiceEntity
}