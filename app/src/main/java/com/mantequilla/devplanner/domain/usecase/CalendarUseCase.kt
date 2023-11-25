package com.mantequilla.devplanner.domain.usecase

import com.mantequilla.devplanner.data.repository.CalendarRepository
import com.mantequilla.devplanner.domain.item.TaskItem
import com.mantequilla.devplanner.presentation.calendar.CalendarState
import com.mantequilla.devplanner.presentation.home.homescreen.HomeScreenState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CalendarUseCase @Inject constructor(private val calendarRepository: CalendarRepository) {
    operator fun invoke(
        select: String,
        userId: String,
        date: String,
        order: String
    ): Flow<CalendarState<List<TaskItem>>> = flow {
        try {
            emit(CalendarState.loading())
            val task = calendarRepository.getTasks(select, userId, date, order)
            emit(CalendarState.success(task))
        } catch (e: Exception) {
            emit(CalendarState.failed(e))
        }
    }
}