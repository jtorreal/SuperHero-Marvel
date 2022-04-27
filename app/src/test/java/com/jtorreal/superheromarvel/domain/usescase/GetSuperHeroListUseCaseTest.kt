package com.jtorreal.superheromarvel.domain.usescase

import com.jtorreal.superheromarvel.data.datasources.remote.EitherSuperHero
import com.jtorreal.superheromarvel.data.datasources.remote.EitherSuperHeroList
import com.jtorreal.superheromarvel.data.datasources.remote.SucessResponse
import com.jtorreal.superheromarvel.data.datasources.remote.superHeroListFakeDataTwo
import com.jtorreal.superheromarvel.domain.model.SuperHeroDomain
import com.jtorreal.superheromarvel.domain.model.Thumbnail
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetSuperHeroListUseCaseTest {

    @Mock
    lateinit var repositoryFake: RepositoryFakeImpl

    @Mock
    lateinit var getSuperHeroDetailUseCase: FakeSuperHeroUseCase

    @Mock
    lateinit var getSuperHeroListUseCase: FakeSuperHerosUseCase

    @Test
    fun `server return 200 code - GetSuperHeroListUseCase`() {

        runBlockingTest {

            whenever(getSuperHeroListUseCase.invoke()).thenReturn(
                eitherSuperHeroListOkCaseOne
            ).let {
                whenever(repositoryFake.getSuperHeroList()).thenReturn(
                    eitherSuperHeroListOkCaseOne
                )
            }

            //Expected response
            val expectedResponse = EitherSuperHeroList(
                success = SucessResponse(
                    "200",
                    superHeroListFakeDataTwo()
                )
            )

            //Check in GetSuperHeroListUseCase
            assertEquals(expectedResponse, getSuperHeroListUseCase.invoke())

            //Check in
            assertEquals(expectedResponse, repositoryFake.getSuperHeroList())
        }
    }

    @Test
    fun `server return 200 code - GetSuperHeroDetailUseCase`() {

        runBlockingTest {

            whenever(getSuperHeroDetailUseCase.invoke("1234")).thenReturn(
                eitherSuperHeroOkCaseOne
            ).let {
                whenever(repositoryFake.getSuperHero("1234")).thenReturn(
                    eitherSuperHeroOkCaseOne
                )
            }

            //Expected response
            val expectedResponse = EitherSuperHero(
                success = SuperHeroDomain(
                    1,
                    name = "Spiderman",
                    description = "SuperHero",
                    thumbnail = Thumbnail(".jpg", "www.photo2.com")
                )
            )


            //Check in GetSuperHeroDetailUseCase
            assertEquals(expectedResponse, getSuperHeroDetailUseCase.invoke("1234"))

            //Check in
            assertEquals(expectedResponse, repositoryFake.getSuperHero("1234"))
        }
    }

}