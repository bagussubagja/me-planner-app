package com.mantequilla.devplanner.data.repository

import com.mantequilla.devplanner.data.remote.Services
import com.mantequilla.devplanner.domain.item.UserItem
import com.mantequilla.devplanner.domain.item.toUserItem
import javax.inject.Inject

class AuthRepository @Inject constructor(private val services: Services) {
    suspend fun getUser(select: String): List<UserItem> {
        return services.getUser(select).map { user->
            user.toUserItem()
        }
    }
}