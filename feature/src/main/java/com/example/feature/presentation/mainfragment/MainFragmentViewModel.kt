package com.example.feature.presentation.mainfragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feature.domain.usecase.GetCharactersUseCase
import com.example.feature.domain.usecase.GetPlanetUseCase
import com.example.feature.domain.usecase.GetStarshipUseCase
import com.example.feature.presentation.rvadapter.DataModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainFragmentViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val getPlanetUseCase: GetPlanetUseCase,
    private val getStarshipUseCase: GetStarshipUseCase,
): ViewModel() {

    enum class SearchCategory(val title: String){
        Characters(title = "Characters"),
        Starships(title = "Starships"),
        Planets(title = "Planets")
    }

    private val _resultList: MutableStateFlow<List<DataModel>> = MutableStateFlow(emptyList())
    val resultList: StateFlow<List<DataModel>>
        get() = _resultList

    private val _searchCategory: MutableStateFlow<SearchCategory> = MutableStateFlow(SearchCategory.Characters)
    val searchCategory: StateFlow<SearchCategory>
        get() = _searchCategory
    init {
        getCharacters()
    }

    fun getCurrentCategory(): SearchCategory{
        return searchCategory.value
    }
    fun setSearchCategory(searchCategory: SearchCategory){
        _searchCategory.value = searchCategory
        when(searchCategory){
            SearchCategory.Characters -> {
                getCharacters()
            }
            SearchCategory.Planets -> {
                getPlanets()
            }
            SearchCategory.Starships -> {
                getStarships()
            }
        }
    }
    private fun getCharacters(){
        viewModelScope.launch {
            val result = getCharactersUseCase()
            result.onSuccess {
            _resultList.emit(it.map {
                DataModel.CharacterInfo(it)
            })
            }
        }
    }

    private fun getPlanets(){
        viewModelScope.launch {
            val result = getPlanetUseCase()
            result.onSuccess {
                _resultList.emit(it.map {
                    DataModel.PlanetInfo(it)
                })
            }
        }
    }

    private fun getStarships(){
        viewModelScope.launch {
            val result = getStarshipUseCase()
            result.onSuccess {
                _resultList.emit(it.map {
                    DataModel.StarshipInfo(it)
                })
            }
        }
    }
}

