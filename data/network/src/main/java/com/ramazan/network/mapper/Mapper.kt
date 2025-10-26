package com.ramazan.network.mapper

import com.ramazan.domain.model.Course
import com.ramazan.network.model.CourseDto
import com.ramazan.network.model.CoursesResponse
import kotlin.Long

fun CourseDto.toDomain() = Course(
    id = id,
    title = title,
    text = text,
    price = price,
    rate = rate,
    startDate = startDate,
    hasLike = hasLike,
    publishDate = publishDate,

)