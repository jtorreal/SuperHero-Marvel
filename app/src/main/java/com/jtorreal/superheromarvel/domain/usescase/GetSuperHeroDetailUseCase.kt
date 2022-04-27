package com.jtorreal.superheromarvel.domain.usescase

import com.jtorreal.superheromarvel.data.network.Result
import com.jtorreal.superheromarvel.data.repository.SuperHeroRepository
import com.jtorreal.superheromarvel.domain.model.SuperHeroDomain
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSuperHeroDetailUseCase @Inject constructor(private val repository: SuperHeroRepository) {

    fun invoke(id: String): Flow<Result<SuperHeroDomain>> {
        return repository.getSuperHeroDetail(id)
    }
}