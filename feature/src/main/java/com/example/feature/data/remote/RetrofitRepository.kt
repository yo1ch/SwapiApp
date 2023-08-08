package com.example.feature.data.remote

import android.util.Log
import com.example.core.network.toResult
import com.example.feature.data.local.FeatureDao
import com.example.feature.data.local.FeatureDatabase
import com.example.feature.data.local.toCharacterEntity
import com.example.feature.data.local.toFilmEntity
import com.example.feature.data.local.toPlanetEntity
import com.example.feature.data.local.toStarshipEntity
import com.example.feature.domain.entity.Character
import com.example.feature.domain.entity.Planet
import com.example.feature.domain.entity.Starship
import com.example.feature.domain.repository.SwapiRepository
import com.example.feature.presentation.rvadapter.DataModel
import com.example.feature.presentation.rvadapter.RvAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import javax.inject.Inject

class RetrofitRepository @Inject constructor(
    private val retrofit: Retrofit,
    private val featureDao: FeatureDao,
) : SwapiRepository {

    private val webApi: WebApi by lazy {
        retrofit.create(WebApi::class.java)
    }


    override suspend fun getCharacters(searchQuery: String): Result<List<Character>> =
        withContext(Dispatchers.IO) {
            try {
                val response = webApi.getCharacters(searchQuery)
                return@withContext response.toResult().map {
                    it.results.map { characterDto ->
                        val isFavorite = featureDao.isCharacterInFavorite(characterDto.name) > 0
                        characterDto.toCharacter(isFavorite)
                    }
                }
            } catch (e: Exception) {
                return@withContext Result.failure(e)
            }

        }
    override suspend fun getPlanets(searchQuery: String): Result<List<Planet>> =
        withContext(Dispatchers.IO) {
            try {
                val response = webApi.getPlanets(searchQuery)
                return@withContext response.toResult().map {
                    it.results.map {
                        it.toPlanet()
                    }
                }
            } catch (e: Exception) {
                return@withContext Result.failure(e)
            }

        }
    override suspend fun getStarships(searchQuery: String): Result<List<Starship>> =
        withContext(Dispatchers.IO) {
            try {
                val response = webApi.getStarships(searchQuery)
                return@withContext response.toResult().map {
                    it.results.map {
                        it.toStarship()
                    }
                }
            } catch (e: Exception) {
                return@withContext Result.failure(e)
            }

        }
    override suspend fun saveFilms(): Result<Unit> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                val response = webApi.getFilms()
                if (response.isSuccessful && response.body() != null) {
                    val films = response.body()?.results ?: emptyList()
                    films.forEach { film ->
                        featureDao.saveFilms(film.toFilmEntity())
                    }
                    Result.success(Unit)
                } else {
                    Result.failure(Exception("Failed to fetch films"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }

        }

    override suspend fun addFavorite(dataModel: DataModel) {
        return when(dataModel){
            is DataModel.PlanetInfo -> {
                featureDao.addFavoritePlanet(dataModel.planet.toPlanetEntity())
            }
            is DataModel.CharacterInfo -> {
                featureDao.addFavoriteCharacter(dataModel.character.toCharacterEntity())
            }
            is DataModel.StarshipInfo -> {
                featureDao.addFavoriteStarship(dataModel.starship.toStarshipEntity())
            }
        }
    }

    override suspend fun deleteFavorite(dataModel: DataModel) {
        return when(dataModel){
            is DataModel.PlanetInfo -> {
                featureDao.deleteFavoritePlanet(dataModel.planet.toPlanetEntity())
            }
            is DataModel.CharacterInfo -> {
                featureDao.deleteFavoriteCharacter(dataModel.character.toCharacterEntity())
            }
            is DataModel.StarshipInfo -> {
                featureDao.deleteFavoriteStarship(dataModel.starship.toStarshipEntity())
            }
        }
    }

}