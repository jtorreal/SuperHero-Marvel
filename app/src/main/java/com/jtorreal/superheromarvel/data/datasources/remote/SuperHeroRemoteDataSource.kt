package com.jtorreal.superheromarvel.data.datasources.remote

import com.jtorreal.superheromarvel.data.datasources.remote.model.response.SuperHeroServer
import com.jtorreal.superheromarvel.data.network.Result
import com.jtorreal.superheromarvel.data.network.SuperHeroApiClient
import com.jtorreal.superheromarvel.data.network.SuperHeroService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SuperHeroRemoteDataSource @Inject constructor(private val apiClient: SuperHeroApiClient) :
    ICharacterRemoteDataSource {

    override fun getSuperHeroListSource(): Flow<Result<List<SuperHeroServer>>> = flow {

        val superHeroListResponse = SuperHeroService.getSuperHeroList(apiClient)

        emit(superHeroListResponse)
    }

    override fun getSuperHeroDetailSource(id: String): Flow<Result<List<SuperHeroServer>>> = flow {

        val superHero = SuperHeroService.getSuperHero(apiClient, id)

        emit(superHero)
    }

}




