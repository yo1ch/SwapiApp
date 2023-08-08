package com.example.feature.presentation.mainfragment

import android.provider.ContactsContract.Data
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.view.DataState
import com.example.feature.domain.usecase.AddFavoriteUseCase
import com.example.feature.domain.usecase.AddFavoriteUseCaseImpl
import com.example.feature.domain.usecase.DeleteFavoriteUseCase
import com.example.feature.domain.usecase.GetCharactersUseCase
import com.example.feature.domain.usecase.GetPlanetUseCase
import com.example.feature.domain.usecase.GetStarshipUseCase
import com.example.feature.domain.usecase.SaveFilmsUseCase
import com.example.feature.domain.usecase.SaveFilmsUseCaseImpl
import com.example.feature.presentation.rvadapter.DataModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainFragmentViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val getPlanetUseCase: GetPlanetUseCase,
    private val getStarshipUseCase: GetStarshipUseCase,
    private val saveFilmsUseCase: SaveFilmsUseCase,
    private val addFavoriteUseCase: AddFavoriteUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase,
) : ViewModel() {

    enum class SearchCategory(val title: String) {
        Characters(title = "Characters"),
        Starships(title = "Starships"),
        Planets(title = "Planets")
    }

    init {
        viewModelScope.launch {
            saveFilmsUseCase()
        }
    }

    private val _resultList: MutableStateFlow<DataState<List<DataModel>>> =
        MutableStateFlow(DataState.Success(emptyList()))
    val resultList: StateFlow<DataState<List<DataModel>>>
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
        _resultList.value = DataState.Success(emptyList())
    }

    private fun getCharacters(searchQuery: String) {
        _resultList.value = DataState.Loading()
        viewModelScope.launch {
            val result = getCharactersUseCase(searchQuery)
            result.onSuccess {
                _resultList.emit(
                    DataState.Success(
                        it.map {
                            DataModel.CharacterInfo(it)
                        }
                    )
                )
            }
        }
    }

    private fun getPlanets(searchQuery: String) {
        _resultList.value = DataState.Loading()
        viewModelScope.launch {
            Result
            val result = getPlanetUseCase(searchQuery)
            result.onSuccess {
                _resultList.emit(
                    DataState.Success(
                        it.map {
                            DataModel.PlanetInfo(it)
                        }
                    )
                )
            }
        }
    }

    private fun getStarships(searchQuery: String) {
        _resultList.value = DataState.Loading()
        viewModelScope.launch {
            val result = getStarshipUseCase(searchQuery)
            result.onSuccess {
                _resultList.emit(
                    DataState.Success(
                        it.map {
                            DataModel.StarshipInfo(it)
                        }
                    )
                )
            }
        }
    }

    fun getData(searchQuery: String, searchCategory: SearchCategory) {
        when (searchCategory) {
            SearchCategory.Starships -> getStarships(searchQuery)
            SearchCategory.Planets -> getPlanets(searchQuery)
            SearchCategory.Characters -> getCharacters(searchQuery)
        }
    }

    fun toggleFavorite(dataModel: DataModel, favoriteState: Boolean) {
        viewModelScope.launch {
            if (favoriteState) addFavoriteUseCase(dataModel)
            else deleteFavoriteUseCase(dataModel)
        }
    }
}

