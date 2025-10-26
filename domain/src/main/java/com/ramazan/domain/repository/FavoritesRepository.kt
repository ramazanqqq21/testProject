package com.ramazan.domain.repository

import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {
    fun observeFavoriteIds(): Flow<Set<Long>>
    suspend fun toggleFavorite(courseId: Long)
    suspend fun setFavorite(courseId: Long, favorite: Boolean)
}