package com.mantequilla.devplanner.data.remote

import com.mantequilla.devplanner.data.model.user.UserModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class Services @Inject constructor(private val api: Api) {
    suspend fun getUser(select: String): List<UserModel> {
        return withContext(Dispatchers.IO) {
            val user = api.getUser(select)
            user.body() ?: emptyList()
        }
    }
}