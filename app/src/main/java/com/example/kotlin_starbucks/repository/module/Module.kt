package com.example.kotlin_starbucks.repository.module

import com.example.kotlin_starbucks.repository.DataSource
import com.example.kotlin_starbucks.repository.RemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class Module {

    @Binds
    abstract fun bindRepository(remoteDataSource: RemoteDataSource): DataSource
}