package com.jtorreal.superheromarvel.data.datasources.remote

import com.jtorreal.superheromarvel.data.datasources.remote.model.response.SuperHeroServer
import com.jtorreal.superheromarvel.data.network.Result
import kotlinx.coroutines.flow.Flow

interface ICharacterRemoteDataSource {
    fun getSuperHeroListSource(): Flow<Result<List<SuperHeroServer>>>
    fun getSuperHeroDetailSource(id: String): Flow<Result<List<SuperHeroServer>>>
}