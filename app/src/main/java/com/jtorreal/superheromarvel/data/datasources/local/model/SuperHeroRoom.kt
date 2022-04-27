package com.jtorreal.superheromarvel.data.datasources.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "superhero")
data class SuperHeroRoom(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val name: String?,
    val description: String?,
    val thumbnail: Thumbnail?
)

