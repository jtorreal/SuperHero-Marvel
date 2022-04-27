package com.jtorreal.superheromarvel.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jtorreal.superheromarvel.domain.model.SuperHeroDomain
import com.jtorreal.superheromarvel.domain.model.Thumbnail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FakeSuperHeroListViewModel(
    private val fakeSuperHeroListUseCase: FakeListSuperHeroUseCase,
    private val fakeSuperHeroUseCase: FakeSuperHeroUseCase
) : ViewModel() {

    private val _superheros = MutableLiveData<List<SuperHeroDomain>>()

    val superHeros: LiveData<List<SuperHeroDomain>> get() = _superheros

    fun onLoadCharacterList() {

        viewModelScope.launch(Dispatchers.Main) {
            _superheros.value = fakeSuperHeroListUseCase.invoke()
        }

    }

    fun onLoadCharacter(idCharacter: String?) {

        viewModelScope.launch(Dispatchers.Main) {
            _superheros.value = fakeSuperHeroUseCase.invoke(idCharacter)
        }

    }


    class FakeListSuperHeroUseCase {

        suspend fun invoke(): List<SuperHeroDomain> =
            withContext(Dispatchers.IO) {
                delay(2000)
                characterListFakeData()
            }
    }

    class FakeSuperHeroUseCase {

        suspend fun invoke(idSuperHero: String?): List<SuperHeroDomain> =
            withContext(Dispatchers.IO) {
                superHeroFakeData(idSuperHero)
            }
    }
}


fun characterListFakeData(): List<SuperHeroDomain> {
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

fun superHeroFakeData(idSuperHero: String?): List<SuperHeroDomain> {
    return listOf(
        SuperHeroDomain(
            id = idSuperHero?.toInt(),
            name = "Name 1",
            description = "Hero 1 strong",
            thumbnail = Thumbnail(".jpg", "www.photo1.com")
        )
    )
}






