package com.ramazan.domain.usecase

import com.ramazan.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteIdsFlow @Inject constructor(private val repo: FavoritesRepository) {
    operator fun invoke(): Flow<Set<Long>> = repo.observeFavoriteIds()
}