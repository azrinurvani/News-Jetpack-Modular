package com.example.newsinshort.data.datasource

import android.util.Log
import com.example.newsinshort.data.api.ApiService
import com.example.newsinshort.data.entity.NewsResponse
import retrofit2.Response
import javax.inject.Inject

class NewsDataSourceImpl @Inject constructor(
    private val apiService: ApiService
) : NewsDataSource {
    override suspend fun getNewsHeadlines(country: String): Response<NewsResponse> {
        Log.d("NewsDataSource", "Fetching News Data Source ${apiService.getNewsHeadlines(country = country)}")
        return apiService.getNewsHeadlines(country = country)
    }
}