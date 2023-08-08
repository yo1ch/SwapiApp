package com.example.feature.domain.repository

import com.example.feature.domain.entity.Character
import com.example.feature.domain.entity.Planet
import com.example.feature.domain.entity.Starship

interface SwapiRepository {

    suspend fun getCharacters(searchQuery: String): Result<List<Character>>

    suspend fun getPlanets(searchQuery: String): Result<List<Planet>>

    suspend fun getStarships(searchQuery: String): Result<List<Starship>>

}