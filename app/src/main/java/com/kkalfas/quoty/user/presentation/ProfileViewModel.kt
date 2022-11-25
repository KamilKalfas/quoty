package com.kkalfas.quoty.user.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kkalfas.quoty.app.AppDispatchers
import com.kkalfas.quoty.app.Async
import com.kkalfas.quoty.app.Async.Companion.then
import com.kkalfas.quoty.session.domain.IsSessionActiveUseCase
import com.kkalfas.quoty.session.domain.LoginUseCase
import com.kkalfas.quoty.session.domain.LogoutUseCase
import com.kkalfas.quoty.user.domain.GetUserUseCase
import com.kkalfas.quoty.user.domain.model.UserModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.updateAndGet
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val STATE_KEY_ERROR_MESSAGE = "error_message"

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val dispatchers: AppDispatchers,
    private val loginUseCase: LoginUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val isSessionActiveUseCase: IsSessionActiveUseCase,
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {

    private val _errorMessage = savedStateHandle.getStateFlow(STATE_KEY_ERROR_MESSAGE, "")
    private val _isSessionActive = MutableStateFlow(false)
    private val _profile: MutableStateFlow<UserModel?> = MutableStateFlow(null)

    init {
        checkSessionAndGetUserIfActive()
    }

    val state: StateFlow<ProfileUiState> = combine(
        _isSessionActive, _profile, _errorMessage
    ) { isSessionActive, profile, errorMessage ->
        ProfileUiState(
            isSessionActive = isSessionActive,
            profile = profile,
            errorMessage = errorMessage
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ProfileUiState()
    )

    fun onLoginAction(login: String, password: String) {
        viewModelScope.launch(dispatchers.io) {
            Async.execute {
                loginUseCase(login, password)
            }.then(
                onError = {
                    savedStateHandle[STATE_KEY_ERROR_MESSAGE] = it::class.simpleName
                    clearError()
                },
                onSuccess = { checkSessionAndGetUserIfActive() }
            )
        }
    }

    fun onLogoutAction() {
        viewModelScope.launch(dispatchers.io) {
            Async.execute {
                logoutUseCase()
            }.then(
                onError = {
                    savedStateHandle[STATE_KEY_ERROR_MESSAGE] = it::class.simpleName
                    clearError()
                },
                onSuccess = {
                    _isSessionActive.emit(isSessionActiveUseCase())
                    _profile.update { null }
                }
            )
        }
    }

    private fun checkSessionAndGetUserIfActive() {
        viewModelScope.launch(dispatchers.io) {
            val isActive = _isSessionActive.updateAndGet { isSessionActiveUseCase() }
            if (isActive) _profile.update { getUserUseCase() }
        }
    }

    private suspend fun clearError() {
        delay(1000)
        savedStateHandle[STATE_KEY_ERROR_MESSAGE] = ""
    }
}

data class ProfileUiState(
    val isSessionActive: Boolean = false,
    val profile: UserModel? = null,
    val errorMessage: String = ""
)
