package com.josetorres.marvel.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jtorreal.superheromarvel.R
import com.jtorreal.superheromarvel.SuperHeroApplication
import com.jtorreal.superheromarvel.data.network.Result
import com.jtorreal.superheromarvel.domain.usescase.GetSuperHeroDetailUseCase
import com.jtorreal.superheromarvel.ui.UiResult
import com.jtorreal.superheromarvel.ui.detail.UiStateSuperHeroDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SuperHeroDetailViewModel @Inject constructor(
    private val detailUseCase: GetSuperHeroDetailUseCase
) : ViewModel() {

    private val _progressVisible = MutableStateFlow(false)
    val progressVisible: StateFlow<Boolean> get() = _progressVisible

    private val _result = MutableStateFlow(UiStateSuperHeroDetail(result = UiResult.LOADING))
    val result: StateFlow<UiStateSuperHeroDetail> get() = _result


    fun loadCharacterDetail(idCharacter: String?) {

        viewModelScope.launch {

            if (!idCharacter.isNullOrEmpty()) {

                detailUseCase.invoke(idCharacter).collect {

                    when (it) {

                        is Result.Success -> {

                            _progressVisible.value = false

                            _result.value =
                                UiStateSuperHeroDetail(UiResult.OK, superHero = it.body)
                        }

                        is Result.Error -> {

                            _progressVisible.value = false

                            _result.value = UiStateSuperHeroDetail(
                                UiResult.KO,
                                errorMessage = it.errorMessage
                            )
                        }
                    }
                }

            } else {
                _result.value =
                    UiStateSuperHeroDetail(
                        UiResult.KO,
                        errorMessage = SuperHeroApplication.applicationContext().resources.getString(R.string.api_response_detail_character)
                    )
            }
        }
    }


}