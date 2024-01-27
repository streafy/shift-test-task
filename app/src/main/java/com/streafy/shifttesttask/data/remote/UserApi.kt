package com.streafy.shifttesttask.data.remote

import com.streafy.shifttesttask.data.remote.model.RemoteResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {

    @GET(".")
    suspend fun getUsers(
        @Query("page") page: Int,
        @Query("results") results: Int
    ): RemoteResponse
}