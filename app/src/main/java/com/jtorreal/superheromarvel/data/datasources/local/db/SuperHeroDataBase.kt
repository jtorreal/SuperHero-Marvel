package com.jtorreal.superheromarvel.data.datasources.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jtorreal.superheromarvel.data.datasources.local.model.SuperHeroRoom

@Database(entities = [SuperHeroRoom::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class SuperHeroDataBase : RoomDatabase() {
    abstract fun superHeroDao(): SuperHeroDao
}