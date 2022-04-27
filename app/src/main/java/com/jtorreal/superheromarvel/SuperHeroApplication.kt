package com.jtorreal.superheromarvel

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SuperHeroApplication() : Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: SuperHeroApplication? = null

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
    }
}

