package com.ramazan.network.api

import com.ramazan.network.model.CoursesResponse
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface CoursesApi {
    @GET("u/0/uc")
    suspend fun downloadCourses(
        @Query("id") id: String,
        @Query("export") export: String = "download"
    ): ResponseBody
}