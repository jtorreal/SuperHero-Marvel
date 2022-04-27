package com.jtorreal.superheromarvel.domain.usescase

import com.jtorreal.superheromarvel.data.datasources.remote.EitherSuperHero
import com.jtorreal.superheromarvel.data.datasources.remote.EitherSuperHeroList
import com.jtorreal.superheromarvel.data.datasources.remote.SucessResponse
import com.jtorreal.superheromarvel.data.datasources.remote.superHeroListFakeDataOne
import com.jtorreal.superheromarvel.domain.model.SuperHeroDomain
import com.jtorreal.superheromarvel.domain.model.Thumbnail

class FakeSuperHerosUseCase(private val repository: RepositoryFakeImpl) {

    suspend fun invoke(): EitherSuperHeroList {
        return repository.getSuperHeroList()
    }
}

class FakeSuperHeroUseCase(private val repository: RepositoryFakeImpl) {

    suspend fun invoke(idSuperHero: String): EitherSuperHero {
        return repository.getSuperHero(idSuperHero)
    }
}

class RepositoryFakeImpl() : ISuperHeroRepositoryFake {
    override suspend fun getSuperHeroList(): EitherSuperHeroList {
        return eitherSuperHeroListOkCaseOne
    }

    override suspend fun getSuperHero(id: String): EitherSuperHero {
        return eitherSuperHeroOkCaseOne
    }
}

/**FAKE EITHER REPOSITORY IMPLEMENTATION**/
interface ISuperHeroRepositoryFake {
    suspend fun getSuperHeroList(): EitherSuperHeroList
    suspend fun getSuperHero(id: String): EitherSuperHero
}

/**RESPONSE OK SUPERHERO LIST**/
val eitherSuperHeroListOkCaseOne =
    EitherSuperHeroList(success = SucessResponse("200", superHeroListFakeDataOne()))

/**RESPONSE OK SUPERHERO**/
val eitherSuperHeroOkCaseOne =
    EitherSuperHero(success = SuperHeroDomain(1, name="Spiderman", description="SuperHero", thumbnail = Thumbnail(".jpg", "www.photo2.com")))




