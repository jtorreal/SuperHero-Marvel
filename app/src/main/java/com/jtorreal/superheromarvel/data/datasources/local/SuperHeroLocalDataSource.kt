package com.jtorreal.superheromarvel.data.datasources.local

import com.josetorres.marvel.data.toDomainSuperHero
import com.josetorres.marvel.data.toRoomSuperHero
import com.jtorreal.superheromarvel.R
import com.jtorreal.superheromarvel.SuperHeroApplication
import com.jtorreal.superheromarvel.data.network.Result
import com.jtorreal.superheromarvel.data.datasources.local.db.SuperHeroDao
import com.jtorreal.superheromarvel.domain.model.SuperHeroDomain
import javax.inject.Inject

class SuperHeroLocalDataSource @Inject constructor(private val superHeroDao: SuperHeroDao) :
    ISuperHeroLocalDataSource {

    override suspend fun saveSuperHeros(superHeroList: List<SuperHeroDomain>) {

        superHeroDao.insertSuperHero(superHeroList.map {
            it.toRoomSuperHero()
        })

    }

    override suspend fun getSuperHeros(): Result<List<SuperHeroDomain>> {

        return if (!superHeroDao.getAll().isNullOrEmpty()) {
            Result.Success(body = superHeroDao.getAll().map {
                it.toDomainSuperHero()
            })
        } else {
            Result.Error(errorMessage = SuperHeroApplication.applicationContext().resources.getString(R.string.superhero_list_error))
        }
    }

    override suspend fun getSuperHero(superHeroId: Int): Result<SuperHeroDomain> {

        return if (superHeroDao.getSuperHero(superHeroId) != null) {
            Result.Success(body = superHeroDao.getSuperHero(superHeroId).toDomainSuperHero())
        } else {
            Result.Error(errorMessage = SuperHeroApplication.applicationContext().resources.getString(R.string.superhero_detail_selected_error))
        }
    }
}

