package com.example.feature.presentation.favoritefragment

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feature.domain.entity.Character
import com.example.feature.domain.entity.Film
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
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
            combine(getFavoriteCharactersUseCase(), getFilmsUseCase()) { characters, films ->
                val resultList = mutableListOf<DataModel>()
                films.forEach { film ->
                    var setFilmFlag = -1
                    characters.forEach { character ->
                        if (character.films.contains(film.url)) {
                            if (setFilmFlag < 0) {
                                resultList.add(DataModel.FilmInfo(film))
                                resultList.add(DataModel.CharacterInfo(character))
                                setFilmFlag++
                            } else resultList.add(DataModel.CharacterInfo(character))
                        }
                    }
                }
                _resultList.value = resultList
            }.launchIn(CoroutineScope(Dispatchers.Default))
        }
    }

    private fun getPlanets() {
        viewModelScope.launch {
            combine(getFavoritePlanetsUseCase(), getFilmsUseCase()) { planets, films ->
                val resultList = mutableListOf<DataModel>()
                films.forEach { film ->
                    var setFilmFlag = -1
                    planets.forEach { planet ->
                        if (planet.films.contains(film.url)) {
                            if (setFilmFlag < 0) {
                                resultList.add(DataModel.FilmInfo(film))
                                resultList.add(DataModel.PlanetInfo(planet))
                                setFilmFlag++
                            } else resultList.add(DataModel.PlanetInfo(planet))
                        }
                    }
                }
                _resultList.value = resultList
            }.launchIn(CoroutineScope(Dispatchers.Default))
        }
    }

    private fun getStarships() {
        viewModelScope.launch {
            combine(getFavoriteStarshipsUseCase(), getFilmsUseCase()) { starships, films ->
                val resultList = mutableListOf<DataModel>()
                films.forEach { film ->
                    var setFilmFlag = -1
                    starships.forEach { starship ->
                        if (starship.films.contains(film.url)) {
                            if (setFilmFlag < 0) {
                                resultList.add(DataModel.FilmInfo(film))
                                resultList.add(DataModel.StarshipInfo(starship))
                                setFilmFlag++
                            } else resultList.add(DataModel.StarshipInfo(starship))
                        }
                    }
                }
                _resultList.value = resultList
            }.launchIn(CoroutineScope(Dispatchers.Default))
        }
    }

    fun removeFavorite(dataModel: DataModel) {
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