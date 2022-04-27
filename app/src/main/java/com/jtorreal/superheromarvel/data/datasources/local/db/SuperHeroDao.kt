package com.jtorreal.superheromarvel.data.datasources.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jtorreal.superheromarvel.data.datasources.local.model.SuperHeroRoom
import com.jtorreal.superheromarvel.domain.model.SuperHeroDomain
import kotlinx.coroutines.flow.Flow


@Dao
interface SuperHeroDao {

    @Query("SELECT * FROM superhero")
    suspend fun getAll(): List<SuperHeroRoom>

    @Query("SELECT * FROM superhero WHERE id = :id")
    suspend fun getSuperHero(id: Int): SuperHeroRoom

    @Query("SELECT COUNT(id) FROM superhero")
    fun superHeroCount(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSuperHero(roomListSuperHero: List<SuperHeroRoom>)
}