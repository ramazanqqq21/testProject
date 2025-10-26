package com.ramazan.network.mapper

import com.ramazan.domain.model.Course
import com.ramazan.network.model.CourseDto

fun CourseDto.toDomain() = Course(id = id, title = title, author = author)