package com.mantequilla.devplanner.domain.usecase

import com.mantequilla.devplanner.data.repository.TaskRepository
import com.mantequilla.devplanner.presentation.detail.DetailState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.ResponseBody
import javax.inject.Inject

class DeleteTaskUseCase @Inject constructor(private val taskRepository: TaskRepository) {
    operator fun invoke(id: String): Flow<DetailState<ResponseBody>> = flow {
        try {
            emit(DetailState.loading())
            taskRepository.deleteTask(id)
            emit(DetailState.success(204))
        } catch (e: Exception) {
            emit(DetailState.failed(e))
        }
    }
}