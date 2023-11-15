package com.mantequilla.devplanner.data.remote

import android.util.Log
import com.mantequilla.devplanner.data.model.auth.AuthModel
import com.mantequilla.devplanner.data.model.task.TaskModel
import com.mantequilla.devplanner.data.model.user.UserModel
import com.mantequilla.devplanner.data.params.AuthParams
import com.mantequilla.devplanner.data.params.TaskParams
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import javax.inject.Inject

class Services @Inject constructor(private val api: Api) {
    suspend fun authLogin(authParams: AuthParams) : AuthModel {
        return withContext(Dispatchers.IO) {
            val authLogin = api.authLogin(authParams)
            authLogin.body()!!
        }
    }
    suspend fun getUser(select: String, userId: String): List<UserModel> {
        return withContext(Dispatchers.Main) {
            val user = api.getUser(select, userId)
            user.body() ?: emptyList()
        }
    }

    suspend fun getTasks(select: String, userId: String, date: String, order: String): List<TaskModel> {
        return withContext(Dispatchers.IO) {
            val tasks = api.getTasks(select, userId, date, order)
            tasks.body() ?: emptyList()
        }
    }

    suspend fun addTask(taskParams: TaskParams) : ResponseBody {
        return withContext(Dispatchers.IO) {
            val task = api.addTask(taskParams)
            task.body()!!
        }
    }
}