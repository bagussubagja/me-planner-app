package com.mantequilla.devplanner.presentation.auth.register

import java.lang.Exception

sealed class AuthRegisterState<out T> {
    data class RegisterSuccess<out T>(val data: T): AuthRegisterState<T>()
    data class RegisterFailed(val exception: Exception): AuthRegisterState<Nothing>()
    data object Loading: AuthRegisterState<Nothing>()
    data object Initial: AuthRegisterState<Nothing>()
    companion object {
        fun <T> success(data: T): RegisterSuccess<T> = RegisterSuccess(data)
        fun failed(exception: Exception): RegisterFailed = RegisterFailed(exception)
        fun loading(): Loading = Loading
        fun initial(): Initial = Initial
    }

}