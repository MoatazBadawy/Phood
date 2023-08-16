package com.phood.identity.domain.usecases

import com.phood.identity.domain.entities.User
import com.phood.identity.domain.repository.IdentityRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.never
import org.mockito.Mockito.`when`
import org.mockito.kotlin.verify

class SignInUseCaseTest {

    @Test
    fun `test successful sign in`() = runBlocking {
        // Arrange
        val identityRepository = mock(IdentityRepository::class.java)
        val signInUseCase = SignInUseCase(identityRepository)
        val email = "test@example.com"
        val password = "password"
        val user = User(
            id = 0,
            email,
            password,
        ) // You should define the User class

        // Stub the behavior of the mock
        `when`(identityRepository.signIn(email, password)).thenReturn(user)

        // Act
        val result = signInUseCase.invoke(email, password)

        // Assert
        verify(identityRepository).saveLoggedInStatus(true) // Verify that saveLoggedInStatus was called with true
        assertEquals(true, result)
    }

    @Test
    fun `test unsuccessful sign in`() = runBlocking {
        // Arrange
        val identityRepository = mock(IdentityRepository::class.java)
        val signInUseCase = SignInUseCase(identityRepository)
        val email = "test@example.com"
        val password = "password"
        val user = User(
            id = 0,
            email,
            "incorrectPassword",
        ) // You should define the User class

        // Stub the behavior of the mock
        `when`(identityRepository.signIn(email, password)).thenReturn(user)

        // Act
        val result = signInUseCase.invoke(email, password)

        // Assert
        verify(
            identityRepository,
            never(),
        ).saveLoggedInStatus(false) // Verify that saveLoggedInStatus was not called
        assertEquals(false, result)
    }
}
