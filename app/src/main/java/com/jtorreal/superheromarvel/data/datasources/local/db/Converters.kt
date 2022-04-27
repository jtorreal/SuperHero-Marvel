package com.jtorreal.superheromarvel.data.datasources.local.db

import androidx.room.TypeConverter
import com.jtorreal.superheromarvel.data.datasources.local.model.Thumbnail


class Converters {
    @TypeConverter
    fun fromThumbnailPath(thumbnail: Thumbnail?): String? {
        return thumbnail?.path
    }

    @TypeConverter
    fun toThumbnail(name: String): Thumbnail? {
        return Thumbnail(name, name)
    }
}