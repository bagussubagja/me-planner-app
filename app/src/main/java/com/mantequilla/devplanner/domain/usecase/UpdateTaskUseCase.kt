package com.mantequilla.devplanner.domain.usecase

import android.util.Log
import com.mantequilla.devplanner.data.params.TaskParams
import com.mantequilla.devplanner.data.repository.TaskRepository
import com.mantequilla.devplanner.presentation.detail.DetailState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.ResponseBody
import javax.inject.Inject

class UpdateTaskUseCase @Inject constructor(private val taskRepository: TaskRepository) {
    operator fun invoke(taskParams: TaskParams, id: String): Flow<DetailState<ResponseBody>> = flow {
        try {
            emit(DetailState.loading())
            taskRepository.updateTask(taskParams, id)
            emit(DetailState.success(204))
        } catch (e: Exception) {
            emit(DetailState.failed(e))
        }
    }
}