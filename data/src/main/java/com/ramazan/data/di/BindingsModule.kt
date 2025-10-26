package com.ramazan.data.di

import android.content.Context
import androidx.room.Room
import com.ramazan.data.db.AppDatabase
import com.ramazan.data.db.FavoritesDao
import com.ramazan.data.repository.FavoritesRepositoryImpl
import com.ramazan.domain.repository.FavoritesRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class BindingsModule {
    @Binds
    abstract fun bindFavoritesRepository(impl: FavoritesRepositoryImpl): FavoritesRepository
}


@Module
@InstallIn(SingletonComponent::class)
object ProvidersModule {

    @Provides
    @Singleton
    fun provideDb(
        @ApplicationContext ctx: Context
    ): AppDatabase = Room.databaseBuilder(
        ctx,
        AppDatabase::class.java,
        "app.db"
    ).build()

    @Provides
    fun provideFavoritesDao(db: AppDatabase): FavoritesDao = db.favoritesDao()
}