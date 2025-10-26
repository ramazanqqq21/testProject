package com.ramazan.domain.usecase

import com.ramazan.domain.repository.FavoritesRepository
import javax.inject.Inject

class ToggleFavorite @Inject constructor(private val repo: FavoritesRepository) {
    suspend operator fun invoke(courseId: Long) = repo.toggleFavorite(courseId = courseId)
}