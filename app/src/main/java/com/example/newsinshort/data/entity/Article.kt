package com.example.newsinshort.data.entity


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
//import kotlinx.serialization.SerialName
//import kotlinx.serialization.Serializable

//@Serializable
//data class Article(
//    @SerialName("author")
//    val author: String = "",
//    @SerialName("content")
//    val content: String = "",
//    @SerialName("description")
//    val description: String = "",
//    @SerialName("publishedAt")
//    val publishedAt: String = "",
//    @SerialName("source")
//    val source: Source = Source(),
//    @SerialName("title")
//    val title: String = "",
//    @SerialName("url")
//    val url: String = "",
//    @SerialName("urlToImage")
//    val urlToImage: String = ""
//)

@JsonClass(generateAdapter = true)
data class Article(
    @field:Json(name = "author")
    val author: String = "",

    @field:Json(name = "content")
    val content: String = "",

    @field:Json(name = "description")
    val description: String = "",

    @field:Json(name = "publishedAt")
    val publishedAt: String = "",

    @field:Json(name = "source")
    val source: Source = Source(),

    @field:Json(name = "title")
    val title: String = "",

    @field:Json(name = "url")
    val url: String = "",

    @field:Json(name = "urlToImage")
    val urlToImage: String = ""
)