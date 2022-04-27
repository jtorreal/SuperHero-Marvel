package com.jtorreal.superheromarvel.data.datasources.remote

import com.jtorreal.superheromarvel.domain.model.SuperHeroDomain
import com.jtorreal.superheromarvel.domain.model.Thumbnail
import retrofit2.http.GET
import retrofit2.http.Path

class FakeRemoteDataSource(private val remoteClient: ApiFake) :
    ICharacterRemoteDataSourceFake {

    override suspend fun getSuperHeroList(): EitherSuperHeroList {
        return remoteClient.getSuperHeroListFakeApi()
    }

    override suspend fun getSuperHero(id: String): EitherSuperHero {
        return remoteClient.getSuperHeroFakeApi(id)
    }
}

/**FAKE EITHER**/
interface ICharacterRemoteDataSourceFake {
    suspend fun getSuperHeroList(): EitherSuperHeroList
    suspend fun getSuperHero(id: String): EitherSuperHero
}

/**RESPONSE TYPE**/
data class EitherSuperHeroList(
    val error: ApiError? = null,
    val success: SucessResponse? = null
)

data class EitherSuperHero(val error: ApiError? = null, val success: SuperHeroDomain)

/**Success RESPONSE**/
data class SucessResponse(
    val code: String, val superHeroList: List<SuperHeroDomain>? = null
)

/***API Error Response***/
data class ApiError(val code: String, val message: String)


/**INTERFAZ API**/

interface ApiFake {

    @GET("v1/public/characters")
    suspend fun getSuperHeroListFakeApi() = EitherSuperHeroList()

    @GET("v1/public/characters/{characterId}")
    suspend fun getSuperHeroFakeApi(@Path("characterId") characterId: String): EitherSuperHero

}

/**RESPONSE KO CHARACTER LIST**/
val eitherSuperHeroListKoCaseOne = EitherSuperHeroList(ApiError("409", "Empty parameter"))

val eitherSupeHeroListKoCaseTwo = EitherSuperHeroList(ApiError("404", "Not found character list"))

val eitherSuperHeroListOkCaseOne =
    EitherSuperHeroList(success = SucessResponse("200", superHeroListFakeDataOne()))

/**RESPONSE LIST CHARACTERS**/
fun superHeroListFakeDataOne(): List<SuperHeroDomain> {
    return listOf(
        SuperHeroDomain(
            id = 1,
            name = "Name 1",
            description = "Hero 1 strong",
            thumbnail = Thumbnail(".jpg", "www.photo1.com")
        ),
        SuperHeroDomain(
            id = 2,
            name = "Name 2",
            description = "Hero 2 power",
            thumbnail = Thumbnail(".jpg", "www.photo2.com")
        ),
        SuperHeroDomain(
            id = 3,
            name = "Name 3",
            description = "Hero 3 weak ",
            thumbnail = Thumbnail(".jpg", "www.photo3.com")
        )

    )
}

fun superHeroListFakeDataTwo(): List<SuperHeroDomain> {
    return listOf(
        SuperHeroDomain(
            id = 1,
            name = "Name 1",
            description = "Hero 1 strong",
            thumbnail = Thumbnail(".jpg", "www.photo1.com")
        ),
        SuperHeroDomain(
            id = 2,
            name = "Name 2",
            description = "Hero 2 power",
            thumbnail = Thumbnail(".jpg", "www.photo2.com")
        ),
        SuperHeroDomain(
            id = 3,
            name = "Name 3",
            description = "Hero 3 weak ",
            thumbnail = Thumbnail(".jpg", "www.photo3.com")
        )

    )
}





