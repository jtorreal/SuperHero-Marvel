package com.jtorreal.superheromarvel.domain.usescase

import com.jtorreal.superheromarvel.data.repository.SuperHeroRepository
import javax.inject.Inject

class GetLastTenSuperHeroUseCase @Inject constructor(private val repository: SuperHeroRepository) {

    suspend fun invoke(id: String) {
//        repository.getSuperHeroDetail(id)
    }
}