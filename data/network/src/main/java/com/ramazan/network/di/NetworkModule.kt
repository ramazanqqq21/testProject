package com.ramazan.network.di

import com.ramazan.network.api.CoursesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://drive.usercontent.google.com/")
        .addConverterFactory(ScalarsConverterFactory.create())
        .build()

    @Provides @Singleton
    fun provideCoursesApi(retrofit: Retrofit): CoursesApi =
        retrofit.create(CoursesApi::class.java)
}