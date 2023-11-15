package com.mantequilla.devplanner.data.repository

import com.mantequilla.devplanner.data.remote.Services
import com.mantequilla.devplanner.domain.item.TaskItem
import com.mantequilla.devplanner.domain.item.toTaskItem
import javax.inject.Inject

class CalendarRepository @Inject constructor(private val services: Services) {
    suspend fun getTasks(select: String, userId: String, date: String, order: String) : List<TaskItem> {
        return services.getTasks(select, userId, date, order).map {
            it.toTaskItem()
        }
    }
}