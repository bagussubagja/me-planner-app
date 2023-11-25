package com.mantequilla.devplanner.domain.usecase

import com.mantequilla.devplanner.data.params.AuthParams
import com.mantequilla.devplanner.data.repository.AuthRepository
import com.mantequilla.devplanner.domain.item.AuthItem
import com.mantequilla.devplanner.presentation.auth.login.AuthLoginState
import com.mantequilla.devplanner.presentation.auth.register.AuthRegisterState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRegisterUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    operator fun invoke(authParams: AuthParams): Flow<AuthRegisterState<AuthItem>> = flow {
        try {
            emit(AuthRegisterState.initial())
            emit(AuthRegisterState.loading())
            val user = authRepository.authRegister(authParams)
            emit(AuthRegisterState.success(user))
        } catch (e: Exception) {
            emit(AuthRegisterState.failed(e))
        }
    }
}