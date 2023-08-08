package com.example.feature.presentation.favoritefragment

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feature.domain.usecase.AddFavoriteUseCase
import com.example.feature.domain.usecase.DeleteFavoriteUseCase
import com.example.feature.domain.usecase.GetCharactersUseCase
import com.example.feature.domain.usecase.GetFilmsUseCase
import com.example.feature.domain.usecase.GetPlanetUseCase
import com.example.feature.domain.usecase.GetStarshipUseCase
import com.example.feature.domain.usecase.SaveFilmsUseCase
import com.example.feature.domain.usecase.favorites.GetFavoriteCharactersUseCase
import com.example.feature.domain.usecase.favorites.GetFavoritePlanetsUseCase
import com.example.feature.domain.usecase.favorites.GetFavoriteStarshipsUseCase
import com.example.feature.presentation.mainfragment.MainFragmentViewModel
import com.example.feature.presentation.rvadapter.DataModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoriteFragmentViewModel @Inject constructor(
    private val getFavoriteStarshipsUseCase: GetFavoriteStarshipsUseCase,
    private val getFavoritePlanetsUseCase: GetFavoritePlanetsUseCase,
    private val getFavoriteCharactersUseCase: GetFavoriteCharactersUseCase,
    private val getFilmsUseCase: GetFilmsUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase,
) : ViewModel() {


    enum class SearchCategory(val title: String) {
        Characters(title = "Characters"),
        Starships(title = "Starships"),
        Planets(title = "Planets")
    }

    private val _resultList: MutableStateFlow<List<DataModel>> = MutableStateFlow(emptyList())
    val resultList: StateFlow<List<DataModel>>
        get() = _resultList

    private val _searchCategory: MutableStateFlow<SearchCategory> =
        MutableStateFlow(SearchCategory.Characters)
    val searchCategory: StateFlow<SearchCategory>
        get() = _searchCategory

    fun getCurrentCategory(): SearchCategory {
        return searchCategory.value
    }

    fun setSearchCategory(searchCategory: SearchCategory) {
        _searchCategory.value = searchCategory
    }
    private fun getCharacters() {
        viewModelScope.launch {
            val result = getFavoriteCharactersUseCase()
            result.collect { it ->
                _resultList.value = it.map { DataModel.CharacterInfo(it) }
            }
        }
    }
    private fun getPlanets() {
        viewModelScope.launch {
            val result = getFavoritePlanetsUseCase()
            result.collect { resultList ->
                _resultList.value = resultList.map { DataModel.PlanetInfo(it) }
            }
        }
    }

    private fun getStarships() {
        viewModelScope.launch {
            val result = getFavoriteStarshipsUseCase()
            result.collect { resultList ->
                _resultList.value = resultList.map { DataModel.StarshipInfo(it) }
            }
        }
    }

    fun removeFavorite(dataModel: DataModel){
        viewModelScope.launch {
            deleteFavoriteUseCase(dataModel)
        }
    }
    fun getData(searchCategory: SearchCategory) {
        when (searchCategory) {
            SearchCategory.Starships -> getStarships()
            SearchCategory.Planets -> getPlanets()
            SearchCategory.Characters -> getCharacters()
        }
    }

}