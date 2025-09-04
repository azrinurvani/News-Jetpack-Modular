package com.example.newsinshort.ui.repository

import android.util.Log
import com.example.newsinshort.data.datasource.NewsDataSource
import com.example.newsinshort.data.entity.NewsResponse
import com.example.utilities.ResourceState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val newsDataSource: NewsDataSource
) {

//    suspend fun getNewsHeadlines(country: String) : Response<NewsResponse> {
//        return newsDataSource.getNewsHeadlines(country)
//    }

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
        }catch (t: Throwable){
            Log.e("NEWS-REPO", "Exception when request: ${t::class.java.simpleName} | ${t.localizedMessage}", t)
            emit(ResourceState.Error(errorMessage = t?.localizedMessage.toString() ?: "Some Error in flow"))
        }
    }
}