package dev.techpolis.studservice.interactors

import dev.techpolis.studservice.repositories.user.UserRepo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthInteractor @Inject constructor(
    private val userRepo: UserRepo
) {
}