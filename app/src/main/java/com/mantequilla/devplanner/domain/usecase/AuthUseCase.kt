package com.mantequilla.devplanner.domain.usecase

import com.mantequilla.devplanner.data.repository.AuthRepository
import com.mantequilla.devplanner.domain.item.UserItem
import com.mantequilla.devplanner.presentation.auth.login.AuthLoginState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthUseCase @Inject constructor(private val authRepository: AuthRepository) {
    operator fun invoke(select: String): Flow<AuthLoginState<List<UserItem>>> = flow {
        try {
            emit(AuthLoginState.loading())
            val user = authRepository.getUser(select)
            emit(AuthLoginState.success(user))
        } catch (e: Exception) {
            emit(AuthLoginState.failed(e))
        }
    }
}