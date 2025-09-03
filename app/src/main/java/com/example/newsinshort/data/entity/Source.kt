package com.example.newsinshort.data.entity


//import kotlinx.serialization.SerialName
//import kotlinx.serialization.Serializable
//
//@Serializable
//data class Source(
//    @SerialName("id")
//    val id: String = "",
//    @SerialName("name")
//    val name: String = ""
//)

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Source(
    @field:Json(name = "id")
    val id: String = "",

    @field:Json(name = "name")
    val name: String = ""
)