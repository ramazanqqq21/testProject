package com.ramazan.testproject.model

import com.ramazan.domain.model.Course

data class UiState(
    val courses: List<Course> = emptyList(),
    val favoriteIds: Set<Long> = emptySet()
)
