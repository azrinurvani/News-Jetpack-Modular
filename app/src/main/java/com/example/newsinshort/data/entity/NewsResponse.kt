package com.example.newsinshort.data.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


//import kotlinx.serialization.SerialName
//import kotlinx.serialization.Serializable
//
//@Serializable
//data class NewsResponse(
//    val articles: List<Article> = listOf(),
//    @SerialName("status")
//    val status: String = "",
//    @SerialName("totalResults")
//    val totalResults: Int = 0
//)

@JsonClass(generateAdapter = true)
data class NewsResponse(
    @field:Json(name = "articles")
    val articles: List<Article?>? = listOf(),

    @field:Json(name = "status")
    val status: String = "",

    @field:Json(name = "totalResults")
    val totalResults: Int = 0
)