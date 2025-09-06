package com.example.newsinshort.data.api

import com.example.newsinshort.data.entity.NewsResponse
import com.example.utilities.Constants.Constants

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET("top-headlines")
    suspend fun getNewsHeadlines(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String = Constants.API_KEY
    ) : Response<NewsResponse>
}