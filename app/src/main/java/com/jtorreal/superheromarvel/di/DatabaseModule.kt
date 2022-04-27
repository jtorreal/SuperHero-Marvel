package com.jtorreal.superheromarvel.di

import android.content.Context
import androidx.room.Room
import com.jtorreal.superheromarvel.data.datasources.local.db.SuperHeroDao
import com.jtorreal.superheromarvel.data.datasources.local.db.SuperHeroDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): SuperHeroDataBase {
        return Room.databaseBuilder(
            appContext,
            SuperHeroDataBase::class.java,
            "superhero.db"
        ).build()
    }

    @Provides
    fun provideSuperHeroDao(appDatabase: SuperHeroDataBase): SuperHeroDao {
        return appDatabase.superHeroDao()
    }
}




