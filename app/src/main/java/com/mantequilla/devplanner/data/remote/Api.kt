package com.mantequilla.devplanner.data.remote

import com.mantequilla.devplanner.data.model.auth.AuthModel
import com.mantequilla.devplanner.data.model.user.UserModel
import com.mantequilla.devplanner.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface Api {
    @POST(Constants.AUTH_LOGIN_ENDPOINT)
    suspend fun authLogin(): Response<AuthModel>

    @GET(Constants.USER_ENDPOINT)
    suspend fun getUser(@Query("select") select: String): Response<List<UserModel>>
}