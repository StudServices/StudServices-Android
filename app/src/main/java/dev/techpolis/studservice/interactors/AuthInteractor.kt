package dev.techpolis.studservice.interactors

import dev.techpolis.studservice.repositories.user.UserRepo
import javax.inject.Inject

class AuthInteractor @Inject constructor(
    private val userRepo: UserRepo
) {
}