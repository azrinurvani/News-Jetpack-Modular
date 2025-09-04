package com.example.newsinshort.di

import com.example.newsinshort.data.datasource.NewsDataSource
import com.example.newsinshort.ui.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(
    includes = [
        NetworkModule::class,
        DataSourceModule::class
    ]
)
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideNewsRepository(dataSource: NewsDataSource) : NewsRepository{
        return NewsRepository(dataSource)
    }
}