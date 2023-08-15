package com.moataz.phood.identity.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moataz.phood.identity.domain.usecases.LoggedInStatusUseCase
import com.moataz.phood.identity.domain.usecases.SignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val loggedInStatusUseCase: LoggedInStatusUseCase,
) : ViewModel() {

    val email = MutableStateFlow("mohamed.salah@phood.com")
    val password = MutableStateFlow("Brightskies")

    private val _isEmailValid = MutableStateFlow(true)
    val isEmailValid = _isEmailValid

    private val _isPasswordValid = MutableStateFlow(true)
    val isPasswordValid = _isPasswordValid

    private val _isUserLoggedIn = Channel<Boolean>()
    val isUserLoggedIn get() = _isUserLoggedIn.receiveAsFlow()

    private val _isLoginFailed = Channel<Boolean>()
    val isLoginFailed get() = _isLoginFailed.receiveAsFlow()

    init {
        checkIfUserLoggedIn()
    }

    private fun checkIfUserLoggedIn() {
        viewModelScope.launch {
            val isLoggedIn = loggedInStatusUseCase()
            if (isLoggedIn) {
                _isUserLoggedIn.send(true)
            }
        }
    }

    fun onLoginClicked() {
        if (email.value.isNotEmpty() && password.value.isNotEmpty()) {
            viewModelScope.launch {
                val isUserLoggedIn = signInUseCase(email.value, password.value)
                if (isUserLoggedIn) {
                    checkIfUserLoggedIn()
                } else {
                    _isLoginFailed.send(true)
                }
            }
        } else {
            if (email.value.isEmpty()) {
                _isEmailValid.value = false
            }
            if (password.value.isEmpty()) {
                _isPasswordValid.value = false
            }
        }
    }
}
