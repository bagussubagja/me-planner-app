package com.mantequilla.devplanner.presentation.detail

import java.lang.Exception

sealed class DetailState<out T> {
    data class SuccessUpdateData(val status: Int): DetailState<Nothing>()
    data class ErrorUpdateData(val exception: Exception): DetailState<Nothing>()
    data object Loading: DetailState<Nothing>()
    companion object {
        fun success(status: Int): SuccessUpdateData = SuccessUpdateData(status)
        fun failed(exception: Exception): ErrorUpdateData = ErrorUpdateData(exception)
        fun loading(): Loading = Loading
    }
}