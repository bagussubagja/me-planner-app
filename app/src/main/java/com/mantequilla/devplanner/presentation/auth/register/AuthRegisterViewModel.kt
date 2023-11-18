package com.mantequilla.devplanner.presentation.auth.register

import com.mantequilla.devplanner.presentation.auth.login.AuthLoginState

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.mantequilla.devplanner.data.params.AuthParams
import com.mantequilla.devplanner.domain.item.AuthItem
import com.mantequilla.devplanner.domain.usecase.AuthLoginUseCase
import com.mantequilla.devplanner.domain.usecase.AuthRegisterUseCase
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
class AuthRegisterViewModel @Inject constructor(
    private val authRegisterUseCase: AuthRegisterUseCase,
    private val preferencesManager: PreferencesManager
) : ViewModel() {
    private val _state = MutableStateFlow<AuthRegisterState<AuthItem>>(AuthRegisterState.initial())
    val state: StateFlow<AuthRegisterState<AuthItem>> get() = _state

    fun authRegister(authParams: AuthParams) {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                try {
                    authRegisterUseCase(authParams).collect() { result ->
                        when (result) {
                            is AuthRegisterState.RegisterSuccess -> {
                                _state.value = AuthRegisterState.success(result.data)
                                preferencesManager.saveIdUserInfo(StorageKey.userId, result.data.user?.id!!)
                                preferencesManager.saveEmailUserInfo(StorageKey.userEmail, result.data.user?.email!!)
                            }
                            is AuthRegisterState.Loading -> {}
                            is AuthRegisterState.RegisterFailed -> {
                                _state.value = AuthRegisterState.failed(result.exception)
                            }
                            is AuthRegisterState.Initial -> {}
                        }
                    }
                } catch (e: Exception) {
                    _state.value = AuthRegisterState.failed(Exception("Error Login Request $e"))
                }
            }
        }
    }
}