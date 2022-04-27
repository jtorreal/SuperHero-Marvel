package com.jtorreal.superheromarvel.data.datasources.local

import com.jtorreal.superheromarvel.data.network.Result
import com.jtorreal.superheromarvel.data.datasources.local.model.SuperHeroRoom
import com.jtorreal.superheromarvel.domain.model.SuperHeroDomain
import kotlinx.coroutines.flow.Flow

interface ISuperHeroLocalDataSource {

    suspend fun saveSuperHeros(superHero: List<SuperHeroDomain>)

    suspend fun getSuperHeros(): Result<List<SuperHeroDomain>>

    suspend fun getSuperHero(id: Int): Result<SuperHeroDomain>

}