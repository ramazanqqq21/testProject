package com.ramazan.network.source

import com.ramazan.domain.model.Course
import com.ramazan.network.api.CoursesApi
import com.ramazan.network.mapper.toDomain
import javax.inject.Inject

class CoursesRemoteDataSource @Inject constructor(
    private val api: CoursesApi
) {
    suspend fun getCourses(): List<Course> = api.getCourses().map { it.toDomain() }
}