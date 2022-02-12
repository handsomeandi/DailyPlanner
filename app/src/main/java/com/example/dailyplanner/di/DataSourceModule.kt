package com.example.dailyplanner.di

import com.example.dailyplanner.data.datasource.MainDataSource
import com.example.dailyplanner.data.datasource.MainDataSourceInterface
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    abstract fun bindMainDataSource(mainDataSource: MainDataSource): MainDataSourceInterface
}