package com.mantequilla.devplanner.presentation.addtask

import java.lang.Exception

sealed class AddTaskState<out T> {
    data class SuccessPostData(val status: Int): AddTaskState<Nothing>()
    data class ErrorPostData(val exception: Exception): AddTaskState<Nothing>()
    data object Loading: AddTaskState<Nothing>()
    companion object {
        fun success(status: Int): SuccessPostData = SuccessPostData(status)
        fun failed(exception: Exception): ErrorPostData = ErrorPostData(exception)
        fun loading(): Loading = Loading
    }
}