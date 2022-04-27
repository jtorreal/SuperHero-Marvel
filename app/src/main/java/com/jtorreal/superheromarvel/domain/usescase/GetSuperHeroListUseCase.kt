package com.jtorreal.superheromarvel.domain.usescase

import com.jtorreal.superheromarvel.data.network.Result
import com.jtorreal.superheromarvel.data.repository.SuperHeroRepository
import com.jtorreal.superheromarvel.domain.model.SuperHeroDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetSuperHeroListUseCase @Inject constructor(private val repository: SuperHeroRepository) {

    fun invoke(): Flow<Result<List<SuperHeroDomain>>> {
        return repository.getSuperHeroList()
    }
}