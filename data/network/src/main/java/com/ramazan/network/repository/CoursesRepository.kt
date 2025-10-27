package com.ramazan.network.repository

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ramazan.domain.model.Course
import com.ramazan.network.api.CoursesApi
import com.ramazan.network.model.CoursesResponse
import javax.inject.Inject

class CoursesRepository @Inject constructor(
    private val api: CoursesApi
) {
    suspend fun loadCourses(): List<Course> {
        val response = api.downloadCourses("15arTK7XT2b7Yv4BJsmDctA4Hg-BbS8-q")
        val json = response.string()
        val type = object : TypeToken<CoursesResponse>() {}.type
        Log.d("CoursesRepository", "Loaded courses: ${Gson().fromJson<CoursesResponse>(json, type).courses.size}")
        return Gson().fromJson<CoursesResponse>(json, type).courses
    }
}