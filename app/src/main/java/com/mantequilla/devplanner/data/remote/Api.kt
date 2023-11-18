package com.mantequilla.devplanner.data.remote

import com.mantequilla.devplanner.data.model.auth.AuthModel
import com.mantequilla.devplanner.data.model.task.TaskModel
import com.mantequilla.devplanner.data.model.user.UserModel
import com.mantequilla.devplanner.data.params.AuthParams
import com.mantequilla.devplanner.data.params.TaskParams
import com.mantequilla.devplanner.utils.Constants
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Query

interface Api {
    @POST(Constants.AUTH_LOGIN_ENDPOINT)
    @Headers("apikey: ${Constants.API_KEY}")
    suspend fun authLogin(
        @Body authParams: AuthParams,
        @Query("grant_type") grantType: String = "password"
    ): Response<AuthModel>

    @POST(Constants.REGISTER_ENDPOINT)
    @Headers("apikey: ${Constants.API_KEY}")
    suspend fun authRegister(
        @Body authParams: AuthParams,
    ): Response<AuthModel>


    @GET(Constants.USER_ENDPOINT)
    @Headers("apikey: ${Constants.API_KEY}")
    suspend fun getUser(
        @Query("select") select: String,
        @Query("user_id") userId: String
    ): Response<List<UserModel>>

    @GET(Constants.TASK_ENDPOINT)
    @Headers("apikey: ${Constants.API_KEY}")
    suspend fun getTasks(
        @Query("select") select: String,
        @Query("user_id") userId: String,
        @Query("date") date: String,
        @Query("order") order: String,
    ): Response<List<TaskModel>>

    @POST(Constants.TASK_ENDPOINT)
    @Headers("apikey: ${Constants.API_KEY}")
    suspend fun addTask(
        @Body taskParams: TaskParams
    ) : Response<ResponseBody>

    @PATCH(Constants.TASK_ENDPOINT)
    @Headers("apikey: ${Constants.API_KEY}")
    suspend fun updateTask(
        @Body taskParams: TaskParams,
        @Query("id") id: String
    ) : Response<Int>
}