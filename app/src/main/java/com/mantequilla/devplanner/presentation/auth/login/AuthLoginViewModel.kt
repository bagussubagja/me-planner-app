package com.mantequilla.devplanner.presentation.auth.login

import androidx.lifecycle.ViewModel
import com.mantequilla.devplanner.domain.item.UserItem
import com.mantequilla.devplanner.domain.usecase.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class AuthLoginViewModel @Inject constructor(private val getUserUseCase: AuthUseCase) : ViewModel() {
    private val _state = MutableStateFlow<AuthLoginState<List<UserItem>>>(AuthLoginState.loading())
    val state: StateFlow<AuthLoginState<List<UserItem>>> get() = _state
}