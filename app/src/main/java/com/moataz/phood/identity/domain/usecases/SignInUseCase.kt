package com.moataz.phood.identity.domain.usecases

import com.moataz.phood.identity.domain.repository.IdentityRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val identityRepository: IdentityRepository,
) {
    suspend operator fun invoke(email: String, password: String): Boolean {
        val user = identityRepository.signIn(email, password)
        return if (user.email == email && user.password == password) {
            identityRepository.saveLoggedInStatus(true)
            true
        } else {
            false
        }
    }
}
