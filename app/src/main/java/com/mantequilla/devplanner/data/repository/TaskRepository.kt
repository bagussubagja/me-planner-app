package com.mantequilla.devplanner.data.repository

import com.mantequilla.devplanner.data.params.TaskParams
import com.mantequilla.devplanner.data.remote.Services
import com.mantequilla.devplanner.domain.item.TaskItem
import com.mantequilla.devplanner.domain.item.toTaskItem
import okhttp3.ResponseBody
import javax.inject.Inject

class TaskRepository @Inject constructor(private val services: Services) {
    suspend fun getTasks(select: String, userId: String, date: String, order: String) : List<TaskItem> {
        return services.getTasks(select, userId, date, order).map {
            it.toTaskItem()
        }
    }

    suspend fun addTask(taskParams: TaskParams): ResponseBody {
        return services.addTask(taskParams)
    }

    suspend fun updateTask(taskParams: TaskParams, id: String): Int {
        return services.updateTask(taskParams, id)
    }
}