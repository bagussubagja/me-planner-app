package com.mantequilla.devplanner.presentation.auth.login

import java.lang.Exception

sealed class AuthLoginState<out T> {
    data class LoginSuccess<out T>(val data: T): AuthLoginState<T>()
    data class LoginFailed(val exception: Exception): AuthLoginState<Nothing>()
    data object Loading: AuthLoginState<Nothing>()
    data object Initial: AuthLoginState<Nothing>()
    companion object {
        fun <T> success(data: T): LoginSuccess<T> = LoginSuccess(data)
        fun failed(exception: Exception): LoginFailed = LoginFailed(exception)
        fun loading(): Loading = Loading
        fun initial(): Initial = Initial
    }

}