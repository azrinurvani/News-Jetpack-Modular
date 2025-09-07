package com.example.newsinshort.ui.repository

import com.example.newsinshort.data.datasource.NewsDataSource
import com.example.newsinshort.data.entity.NewsResponse
import com.example.utilities.ResourceState
import com.example.utilities.toErrorMessage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val newsDataSource: NewsDataSource
) {

    fun getNewsHeadlines(country : String) : Flow<ResourceState<NewsResponse>> = flow {
        try {
            emit(ResourceState.Loading())
            val response = newsDataSource.getNewsHeadlines(country)
            if (response.isSuccessful && response.body() != null){
                response.body()?.let {
                    emit(ResourceState.Success(it))
                }
            }else{
                emit(ResourceState.Error("Error fetching news data"))
            }
        }catch (e: Exception){
//            Log.e("ErrorHandler", "Class: ${e::class.qualifiedName}")
//            Log.e("ErrorHandler", "Message: ${e.message}")
//            e.printStackTrace()
            emit(ResourceState.Error(e.toErrorMessage()))
        }
    }
}