package com.kkalfas.quoty.user.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kkalfas.quoty.app.AppDispatchers
import com.kkalfas.quoty.session.domain.IsSessionActiveUseCase
import com.kkalfas.quoty.session.domain.LoginUseCase
import com.kkalfas.quoty.session.domain.LogoutUseCase
import com.kkalfas.quoty.user.domain.GetUserUseCase
import com.kkalfas.quoty.user.domain.model.UserModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.updateAndGet
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val dispatchers: AppDispatchers,
    private val loginUseCase: LoginUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val isSessionActiveUseCase: IsSessionActiveUseCase,
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {

    private val _isSessionActive = MutableStateFlow(false)
    private val _profile: MutableStateFlow<UserModel?> = MutableStateFlow(null)

    init {
        checkSessionAndGetUserIfActive()
    }

    val state: StateFlow<ProfileUiState> = combine(
        _isSessionActive, _profile
    ) { isSessionActive, profile ->
        ProfileUiState(
            isSessionActive = isSessionActive,
            profile = profile
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ProfileUiState()
    )

    fun onLoginAction(login: String, password: String) {
        viewModelScope.launch(dispatchers.io) {
            loginUseCase(login, password)
            checkSessionAndGetUserIfActive()
        }
    }

    fun onLogoutAction() {
        viewModelScope.launch(dispatchers.io) {
            logoutUseCase()
            _isSessionActive.emit(isSessionActiveUseCase())
            _profile.update { null }
        }
    }

    private fun checkSessionAndGetUserIfActive() {
        viewModelScope.launch(dispatchers.io) {
            val isActive = _isSessionActive.updateAndGet { isSessionActiveUseCase() }
            if (isActive) _profile.update { getUserUseCase() }
        }
    }
}

data class ProfileUiState(
    val isSessionActive: Boolean = false,
    val profile: UserModel? = null
)
