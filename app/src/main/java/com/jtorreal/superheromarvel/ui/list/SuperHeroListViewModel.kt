package com.jtorreal.superheromarvel.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jtorreal.superheromarvel.data.network.Result
import com.jtorreal.superheromarvel.domain.usescase.GetSuperHeroListUseCase
import com.jtorreal.superheromarvel.ui.UiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SuperHeroListViewModel @Inject constructor(
    private val listUseCase: GetSuperHeroListUseCase
) : ViewModel() {

    private val _progressVisible = MutableStateFlow(true)
    val progressVisible: StateFlow<Boolean> get() = _progressVisible

    private val _result = MutableStateFlow(UiStateSuperHeroList(result = UiResult.LOADING))
    val result: StateFlow<UiStateSuperHeroList> get() = _result

    init {

        viewModelScope.launch {

            listUseCase.invoke().collect {

                when (it) {

                    is Result.Success -> {

                        _progressVisible.value = false

                        _result.value =
                            UiStateSuperHeroList(UiResult.OK, list = it.body)
                    }

                    is Result.Error -> {

                        _progressVisible.value = false

                        _result.value = UiStateSuperHeroList(
                            UiResult.KO,
                            errorMessage = it.errorMessage
                        )
                    }
                }
            }
        }
    }


}