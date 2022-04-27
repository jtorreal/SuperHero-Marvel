package com.jtorreal.superheromarvel.domain.model


data class SuperHeroDomain(
    val id: Int?,
    val name: String?,
    val description: String?,
    val thumbnail: Thumbnail?,
)

data class Thumbnail(
    val extension: String,
    val path: String
)



