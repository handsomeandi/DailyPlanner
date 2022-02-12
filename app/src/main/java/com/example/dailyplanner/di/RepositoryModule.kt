package com.example.dailyplanner.di

import com.example.dailyplanner.data.repository.MainRepository
import com.example.dailyplanner.domain.repository.MainRepositoryInterface
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindMainRepository(repository: MainRepository): MainRepositoryInterface
}