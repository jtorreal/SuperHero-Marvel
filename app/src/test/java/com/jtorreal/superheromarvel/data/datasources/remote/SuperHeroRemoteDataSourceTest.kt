package com.jtorreal.superheromarvel.data.datasources.remote

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class SuperHeroRemoteDataSourceTest {

    @Mock
    lateinit var remoteClientFake: ApiFake

    @Mock
    lateinit var remoteDataSourceFakeImpl: FakeRemoteDataSource

    @Test
    fun `server return code 200 - case 0`() {

        runBlockingTest {

            whenever(remoteDataSourceFakeImpl.getSuperHeroList()).thenReturn(
                eitherSuperHeroListOkCaseOne
            ).let {
                whenever(remoteClientFake.getSuperHeroListFakeApi()).thenReturn(
                    eitherSuperHeroListOkCaseOne
                )
            }

            //Expected response
            val expectedResponse = EitherSuperHeroList( success = SucessResponse("200",superHeroListFakeDataTwo()))

            //Check in RemoteDataSource
            Assert.assertEquals(expectedResponse, remoteDataSourceFakeImpl.getSuperHeroList())

            //Check in RetrofitClient
            Assert.assertEquals(expectedResponse, remoteClientFake.getSuperHeroListFakeApi())
        }
    }

    @Test
    fun `server return code 409 - case 1`() {

        runBlockingTest {

            whenever(remoteDataSourceFakeImpl.getSuperHeroList()).thenReturn(
                eitherSuperHeroListKoCaseOne
            ).let {
                whenever(remoteClientFake.getSuperHeroListFakeApi()).thenReturn(
                    eitherSuperHeroListKoCaseOne
                )
            }

            //Expected response
            val expectedResponse = EitherSuperHeroList(ApiError("409", "Empty parameter"))

            //Check in RemoteDataSource
            Assert.assertEquals(expectedResponse, remoteDataSourceFakeImpl.getSuperHeroList())

            //Check in RetrofitClient
            Assert.assertEquals(expectedResponse, remoteClientFake.getSuperHeroListFakeApi())
        }
    }

    @Test
    fun `server return code 404 - case 2`() {

        runBlockingTest {

            whenever(remoteDataSourceFakeImpl.getSuperHeroList()).thenReturn(
                eitherSupeHeroListKoCaseTwo
            ).let {
                whenever(remoteClientFake.getSuperHeroListFakeApi()).thenReturn(
                    eitherSupeHeroListKoCaseTwo
                )
            }

            //Expected response
            val expectedResponse = EitherSuperHeroList(ApiError("404", "Not found character list"))

            //Check in RemoteDataSource
            Assert.assertEquals(expectedResponse, remoteDataSourceFakeImpl.getSuperHeroList())

            //Check in RetrofitClient
            Assert.assertEquals(expectedResponse, remoteClientFake.getSuperHeroListFakeApi())
        }
    }
}