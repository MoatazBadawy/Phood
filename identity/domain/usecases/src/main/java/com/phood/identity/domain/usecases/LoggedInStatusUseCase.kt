package com.phood.identity.domain.usecases

import com.phood.identity.domain.repository.IdentityRepository
import javax.inject.Inject

class LoggedInStatusUseCase @Inject constructor(
    private val identityRepository: IdentityRepository,
) {
    suspend operator fun invoke(): Boolean {
        return identityRepository.getLoggedInStatus() ?: false
    }
}
