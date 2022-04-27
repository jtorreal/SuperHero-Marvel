package com.jtorreal.superheromarvel.data.datasources.remote.model

import com.jtorreal.superheromarvel.data.datasources.remote.model.response.Data
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SuperHeroResponse(
    @Json(name = "code")
    val code: Int?,
    @Json(name = "status")
    val status: String?,
    @Json(name = "copyright")
    val copyright: String?,
    @Json(name = "attributionText")
    val attributionText: String?,
    @Json(name = "attributionHTML")
    val attributionHTML: String?,
    @Json(name = "etag")
    val etag: String?,
    @Json(name = "data")
    val data: Data?
)


