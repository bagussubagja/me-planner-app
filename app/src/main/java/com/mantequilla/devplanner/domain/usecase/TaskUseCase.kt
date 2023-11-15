package com.mantequilla.devplanner.domain.usecase

import com.mantequilla.devplanner.data.repository.TaskRepository
import com.mantequilla.devplanner.domain.item.TaskItem
import com.mantequilla.devplanner.presentation.home.homescreen.HomeScreenState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TaskUseCase @Inject constructor(
    private val taskRepository: TaskRepository,
) {
    operator fun invoke(
        select: String,
        userId: String,
        date: String,
        order: String
    ): Flow<HomeScreenState<List<TaskItem>>> = flow {
        try {
            emit(HomeScreenState.loading())
            val task = taskRepository.getTasks(select, userId, date, order)
            emit(HomeScreenState.success(task))
        } catch (e: Exception) {
            emit(HomeScreenState.failed(e))
        }
    }
}