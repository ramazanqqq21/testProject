package com.ramazan.data.repository

import com.ramazan.data.db.FavoriteEntity
import com.ramazan.data.db.FavoritesDao
import com.ramazan.domain.repository.FavoritesRepository
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@Singleton
class FavoritesRepositoryImpl @Inject constructor(
    private val dao: FavoritesDao
) : FavoritesRepository {

    override fun observeFavoriteIds(): Flow<Set<Long>> =
        dao.observeIds().map { it.toSet() }

    override suspend fun toggleFavorite(courseId: Long) {
        if (dao.exists(courseId)) dao.remove(courseId)
        else dao.add(FavoriteEntity(courseId))
    }

    override suspend fun setFavorite(courseId: Long, favorite: Boolean) {
        if (favorite) dao.add(FavoriteEntity(courseId))
        else dao.remove(courseId)
    }
}