package com.ramazan.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritesDao {
    @Query("SELECT courseId FROM favorites")
    fun observeIds(): Flow<List<Long>>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun add(entity: FavoriteEntity)


    @Query("DELETE FROM favorites WHERE courseId=:id")
    suspend fun remove(id: Long)


    @Query("SELECT EXISTS(SELECT 1 FROM favorites WHERE courseId=:id)")
    suspend fun exists(id: Long): Boolean
}