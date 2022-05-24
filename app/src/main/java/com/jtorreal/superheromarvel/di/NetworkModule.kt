package com.jtorreal.superheromarvel.di

import com.jtorreal.superheromarvel.BuildConfig
import com.jtorreal.superheromarvel.data.network.SuperHeroApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    val NAME_TS = "ts"
    val NAME_API = "apikey"
    val NAME_HASH = "hash"

    val BASE_URL = "https://gateway.marvel.com/"
    val TS = "1000"
    val PUBLIC_KEY = BuildConfig.PUBLIC_KEY
    val HASH = BuildConfig.HASH//it is generated outside the app for security


    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {

        val httpClient = OkHttpClient.Builder()

        httpClient.addInterceptor { chain ->
            val original: Request = chain.request()
            val originalHttpUrl: HttpUrl = original.url()

            val url = originalHttpUrl.newBuilder()
                .addQueryParameter(NAME_TS, TS)
                .addQueryParameter(NAME_API, PUBLIC_KEY)
                .addQueryParameter(
                    NAME_HASH,
                    HASH
                ) //it's generate with public and private key
                .build()

            val requestBuilder: Request.Builder = original.newBuilder()
                .url(url)
            val request: Request = requestBuilder.build()
            chain.proceed(request)
        }

        return Retrofit.Builder()
            .client(httpClient.build())
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideSuperHeroApiClient(retrofit: Retrofit): SuperHeroApiClient {
        return retrofit.create(SuperHeroApiClient::class.java)
    }

}