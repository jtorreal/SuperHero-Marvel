package com.jtorreal.superheromarvel.data.network

import com.jtorreal.superheromarvel.R
import com.jtorreal.superheromarvel.SuperHeroApplication
import com.jtorreal.superheromarvel.data.datasources.remote.model.SuperHeroResponse
import com.jtorreal.superheromarvel.data.datasources.remote.model.response.SuperHeroServer
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class SuperHeroService() {

    companion object {

        private fun <T> getRootResponse(response: Response<T>): Result<T> {

            return try {

                if (response.isSuccessful) {

                    Result.Success(body = response.body()!!)

                } else {

                    val message = when (response.code()) {
                        404 -> SuperHeroApplication.applicationContext().resources.getString(R.string.superhero_detail_selected_error)
                        else -> {
                            response.message()
                        }
                    }

                    Result.Error(errorMessage = message)
                }

            } catch (e: HttpException) {
                Result.Error(errorMessage = e.localizedMessage)
            } catch (e: IOException) {
                Result.Error(errorMessage = e.localizedMessage)
            } catch (e: Exception) {
                Result.Error(errorMessage = e.localizedMessage)
            }

        }

        suspend fun getSuperHeroList(apiClient: SuperHeroApiClient): Result<List<SuperHeroServer>> {

            return getResultService(apiClient.getSuperHeroList())
        }

        suspend fun getSuperHero(apiClient: SuperHeroApiClient, superHeroId: String): Result<List<SuperHeroServer>> {

            return getResultService(apiClient.getSuperHero(superHeroId))
        }

        private fun getResultService(response: Response<SuperHeroResponse>): Result<List<SuperHeroServer>> {

            response.let {

                return when (val result = getRootResponse(response)) {

                    is Result.Success -> {

                        result.body.data?.results?.let {
                            Result.Success(body = result.body.data?.results!!)
                        } ?: run {
                            Result.Error(errorMessage =SuperHeroApplication.applicationContext().resources.getString(R.string.error_get_data_service))

                        }
                    }

                    is Result.Error -> Result.Error(errorMessage = result.errorMessage)
                }
            }
        }
    }
}
