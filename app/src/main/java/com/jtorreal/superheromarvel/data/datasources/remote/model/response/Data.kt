package com.jtorreal.superheromarvel.data.datasources.remote.model.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Data(
    @Json(name = "count")
    val count: Int?,
    @Json(name = "limit")
    val limit: Int?,
    @Json(name = "offset")
    val offset: Int?,
    @Json(name = "results")
    val results: List<SuperHeroServer>?,
    @Json(name = "total")
    val total: Int?
)