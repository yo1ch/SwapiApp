package com.example.feature.domain.repository

import com.example.feature.domain.entity.Character
import com.example.feature.domain.entity.Film
import com.example.feature.domain.entity.Planet
import com.example.feature.domain.entity.Starship
import com.example.feature.presentation.rvadapter.DataModel
import kotlinx.coroutines.flow.Flow

interface SwapiRepository {

    suspend fun getCharacters(searchQuery: String): Result<List<Character>>
    suspend fun getPlanets(searchQuery: String): Result<List<Planet>>
    suspend fun getStarships(searchQuery: String): Result<List<Starship>>

    fun getFavoriteCharacters(): Flow<List<Character>>
    fun getFavoritePlanets(): Flow<List<Planet>>
    fun getFavoriteStarships(): Flow<List<Starship>>
    fun getFilms(): Flow<List<Film>>

    suspend fun saveFilms(): Result<Unit>
    suspend fun addFavorite(dataModel: DataModel)
    suspend fun deleteFavorite(dataModel: DataModel)

}