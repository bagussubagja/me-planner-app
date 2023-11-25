package com.mantequilla.devplanner.presentation.home.homescreen

import java.lang.Exception

sealed class HomeScreenState<out T> {
    data class SuccessFetchData<out T>(val data: T): HomeScreenState<T>()
    data class ErrorFetchData(val exception: Exception): HomeScreenState<Nothing>()
    data object Loading: HomeScreenState<Nothing>()
    companion object {
        fun <T> success(data: T): SuccessFetchData<T> = SuccessFetchData(data)
        fun failed(exception: Exception): ErrorFetchData = ErrorFetchData(exception)
        fun loading(): Loading = Loading
    }
}