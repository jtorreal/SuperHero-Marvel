package com.jtorreal.superheromarvel.ui.detail

import com.jtorreal.superheromarvel.domain.model.SuperHeroDomain
import com.jtorreal.superheromarvel.ui.UiResult

data class UiStateSuperHeroDetail(
    val result: UiResult? = null,
    val superHero: SuperHeroDomain? = null,
    val errorMessage: String? = null
)

