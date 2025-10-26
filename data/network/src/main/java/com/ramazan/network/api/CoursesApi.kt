package com.ramazan.network.api

import com.ramazan.network.model.CourseDto
import retrofit2.http.GET

interface CoursesApi {
    @GET("courses")
    suspend fun getCourses(): List<CourseDto>
}
