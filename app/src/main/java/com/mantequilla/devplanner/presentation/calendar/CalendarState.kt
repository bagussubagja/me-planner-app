package com.mantequilla.devplanner.presentation.calendar

import java.lang.Exception

sealed class CalendarState<out T> {
    data class SuccessFetchData<out T>(val data: T): CalendarState<T>()
    data class ErrorFetchData(val exception: Exception): CalendarState<Nothing>()
    data object Loading: CalendarState<Nothing>()
    companion object {
        fun <T> success(data: T): SuccessFetchData<T> = SuccessFetchData(data)
        fun failed(exception: Exception): ErrorFetchData = ErrorFetchData(exception)
        fun loading(): Loading = Loading
    }
}