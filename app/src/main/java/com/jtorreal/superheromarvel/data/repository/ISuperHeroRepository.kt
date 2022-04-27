package com.jtorreal.superheromarvel.data.repository

import com.jtorreal.superheromarvel.data.network.Result
import com.jtorreal.superheromarvel.domain.model.SuperHeroDomain
import kotlinx.coroutines.flow.Flow

interface ISuperHeroRepository {

    fun getSuperHeroList(): Flow<Result<List<SuperHeroDomain>>>

    fun getSuperHeroDetail(idCharacter: String):Flow<Result<SuperHeroDomain>>

}