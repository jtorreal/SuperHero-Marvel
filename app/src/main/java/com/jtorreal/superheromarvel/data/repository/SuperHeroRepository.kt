package com.jtorreal.superheromarvel.data.repository

import com.josetorres.marvel.data.toDomainSuperHero
import com.jtorreal.superheromarvel.data.datasources.local.SuperHeroLocalDataSource
import com.jtorreal.superheromarvel.data.datasources.remote.SuperHeroRemoteDataSource
import com.jtorreal.superheromarvel.data.network.Result
import com.jtorreal.superheromarvel.domain.model.SuperHeroDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SuperHeroRepository
@Inject constructor(
    private val localDataSource: SuperHeroLocalDataSource,
    private val remoteDataSource: SuperHeroRemoteDataSource
) : ISuperHeroRepository {

    override fun getSuperHeroList(): Flow<Result<List<SuperHeroDomain>>> {

        return remoteDataSource.getSuperHeroListSource()
            .map { it ->

                when (it) {

                    is Result.Success -> {
                        val domainList = it.body.map {
                            it.toDomainSuperHero()
                        }

                        localDataSource.saveSuperHeros(domainList)

                        Result.Success(body = domainList)
                    }

                    is Result.Error -> {
                        localDataSource.getSuperHeros()
                    }
                }

            }
            .catch {
                emit(localDataSource.getSuperHeros())
            }
    }

    override fun getSuperHeroDetail(idSuperHero: String): Flow<Result<SuperHeroDomain>> {

        return remoteDataSource.getSuperHeroDetailSource(idSuperHero)

            .map { list ->

                when (list) {

                    is Result.Success -> {

                        val domainList = list.body.map {
                            it.toDomainSuperHero()
                        }

                        Result.Success(body = domainList.first())
                    }

                    is Result.Error -> {
                        localDataSource.getSuperHero(idSuperHero.toInt())
                    }
                }
            }.catch {
                emit(localDataSource.getSuperHero(idSuperHero.toInt()))
            }

    }


}

