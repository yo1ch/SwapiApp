package com.example.feature.data.remote

import android.util.Log
import com.example.core.network.toResult
import com.example.feature.domain.entity.Character
import com.example.feature.domain.entity.Planet
import com.example.feature.domain.entity.Starship
import com.example.feature.domain.repository.SwapiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import javax.inject.Inject

class RetrofitRepository @Inject constructor(
    private val retrofit: Retrofit
): SwapiRepository {

    private val webApi: WebApi by lazy {
        retrofit.create(WebApi::class.java)
    }


    override suspend fun getCharacters(searchQuery: String): Result<List<Character>> = withContext(Dispatchers.IO) {
        try {
            val response = webApi.getCharacters(searchQuery)
            return@withContext response.toResult().map {
                it.results.map {
                    it.toCharacter()
                }
            }
        }catch (e : Exception){
            return@withContext Result.failure(e)
        }

    }

    override suspend fun getPlanets(searchQuery: String): Result<List<Planet>>  = withContext(Dispatchers.IO) {
        try {
            val response = webApi.getPlanets(searchQuery)
            return@withContext response.toResult().map {
                it.results.map {
                    it.toPlanet()
                }
            }
        }catch (e : Exception){
            return@withContext Result.failure(e)
        }

    }

    override suspend fun getStarships(searchQuery: String): Result<List<Starship>> = withContext(Dispatchers.IO) {
        try {
            val response = webApi.getStarships(searchQuery)
            return@withContext response.toResult().map {
                it.results.map {
                    it.toStarship()
                }
            }
        }catch (e : Exception){
            return@withContext Result.failure(e)
        }

    }
}