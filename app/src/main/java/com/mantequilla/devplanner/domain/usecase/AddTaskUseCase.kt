package com.mantequilla.devplanner.domain.usecase

import android.util.Log
import com.mantequilla.devplanner.data.params.TaskParams
import com.mantequilla.devplanner.data.repository.TaskRepository
import com.mantequilla.devplanner.presentation.addtask.AddTaskState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.ResponseBody
import javax.inject.Inject

class AddTaskUseCase @Inject constructor(private val taskRepository: TaskRepository) {
    operator fun invoke(taskParams: TaskParams): Flow<AddTaskState<ResponseBody>> = flow {
        try {
            emit(AddTaskState.loading())
            taskRepository.addTask(taskParams)
            emit(AddTaskState.success(201))
        } catch (e: Exception) {
            Log.d("error add task", "${e.printStackTrace()}")
            emit(AddTaskState.failed(e))
        }
    }
}