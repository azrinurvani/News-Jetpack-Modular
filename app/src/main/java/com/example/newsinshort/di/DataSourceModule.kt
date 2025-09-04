package com.example.newsinshort.di

import com.example.newsinshort.data.api.ApiService
import com.example.newsinshort.data.datasource.NewsDataSource
import com.example.newsinshort.data.datasource.NewsDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(
    includes = [
        NetworkModule::class
    ]
)
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    fun provideNewsDataSource(
        apiService: ApiService
    ) : NewsDataSource{
        return NewsDataSourceImpl(apiService)
    }
}