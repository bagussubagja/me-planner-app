package com.mantequilla.devplanner.domain.usecase

import android.util.Log
import com.mantequilla.devplanner.data.params.AuthParams
import com.mantequilla.devplanner.data.repository.AuthRepository
import com.mantequilla.devplanner.domain.item.AuthItem
import com.mantequilla.devplanner.presentation.auth.login.AuthLoginState
import com.mantequilla.devplanner.utils.PreferencesManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthLoginUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    ) {
    operator fun invoke(authParams: AuthParams): Flow<AuthLoginState<AuthItem>> = flow {
        try {
            emit(AuthLoginState.initial())
            emit(AuthLoginState.loading())
            val user = authRepository.authLogin(authParams)
            emit(AuthLoginState.success(user))
        } catch (e: Exception) {
            emit(AuthLoginState.failed(e))
        }
    }
}