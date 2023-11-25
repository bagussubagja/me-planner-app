package com.mantequilla.devplanner.presentation.auth.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.mantequilla.devplanner.data.params.AuthParams
import com.mantequilla.devplanner.domain.item.AuthItem
import com.mantequilla.devplanner.domain.usecase.AuthLoginUseCase
import com.mantequilla.devplanner.navigation.Graph
import com.mantequilla.devplanner.utils.PreferencesManager
import com.mantequilla.devplanner.utils.StorageKey
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AuthLoginViewModel @Inject constructor(
    private val authLoginUseCase: AuthLoginUseCase,
    private val preferencesManager: PreferencesManager
) : ViewModel() {
    private val _state = MutableStateFlow<AuthLoginState<AuthItem>>(AuthLoginState.initial())
    val state: StateFlow<AuthLoginState<AuthItem>> get() = _state

    fun authLogin(authParams: AuthParams) {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                try {
                    authLoginUseCase(authParams).collect() { result ->
                        when (result) {
                            is AuthLoginState.LoginSuccess -> {
                                _state.value = AuthLoginState.success(result.data)
                                preferencesManager.saveIdUserInfo(StorageKey.userId, result.data.user?.id!!)
                                preferencesManager.saveEmailUserInfo(StorageKey.userEmail, result.data.user?.email!!)
                            }
                            is AuthLoginState.Loading -> {}
                            is AuthLoginState.LoginFailed -> {
                                _state.value = AuthLoginState.failed(result.exception)
                            }
                            is AuthLoginState.Initial -> {}
                        }
                    }
                } catch (e: Exception) {
                    _state.value = AuthLoginState.failed(Exception("Error Login Request $e"))
                }
            }
        }
    }
}