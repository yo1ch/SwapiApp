package com.example.feature.domain.repository

import com.example.feature.domain.entity.Character
import com.example.feature.domain.entity.Planet
import com.example.feature.domain.entity.Starship

interface SwapiRepository {

    suspend fun getCharacters(): Result<List<Character>>

    suspend fun getPlanets(): Result<List<Planet>>

    suspend fun getStarships(): Result<List<Starship>>

}