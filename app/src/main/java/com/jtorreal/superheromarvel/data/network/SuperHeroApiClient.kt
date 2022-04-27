package com.jtorreal.superheromarvel.data.network

import com.jtorreal.superheromarvel.data.datasources.remote.model.SuperHeroResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SuperHeroApiClient {

    @GET("v1/public/characters")
    suspend fun getSuperHeroList(): Response<SuperHeroResponse>

    @GET("v1/public/characters/{characterId}")
    suspend fun getSuperHero(@Path("characterId") characterId: String): Response<SuperHeroResponse>

}