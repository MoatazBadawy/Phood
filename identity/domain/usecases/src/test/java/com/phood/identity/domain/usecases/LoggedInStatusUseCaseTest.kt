package com.phood.identity.domain.usecases

import com.phood.identity.domain.repository.IdentityRepository
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class LoggedInStatusUseCaseTest {

    @Test
    fun `test invoke when logged in`() = runBlocking {
        // Arrange
        val identityRepository: IdentityRepository = mock()
        whenever(identityRepository.getLoggedInStatus()).thenReturn(true)
        val loggedInStatusUseCase =
            LoggedInStatusUseCase(identityRepository)

        // Act
        val result = loggedInStatusUseCase.invoke()

        // Assert
        assert(result)
    }

    @Test
    fun `test invoke when not logged in`() = runBlocking {
        // Arrange
        val identityRepository: IdentityRepository = mock()
        whenever(identityRepository.getLoggedInStatus()).thenReturn(false)
        val loggedInStatusUseCase =
            LoggedInStatusUseCase(identityRepository)

        // Act
        val result = loggedInStatusUseCase.invoke()

        // Assert
        assert(!result)
    }
}
