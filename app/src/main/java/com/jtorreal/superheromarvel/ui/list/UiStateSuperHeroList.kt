package com.jtorreal.superheromarvel.ui.list

import com.jtorreal.superheromarvel.domain.model.SuperHeroDomain
import com.jtorreal.superheromarvel.ui.UiResult

data class UiStateSuperHeroList(
    val result: UiResult? = null,
    val list: List<SuperHeroDomain>? = null,
    val errorMessage: String? = null
)



