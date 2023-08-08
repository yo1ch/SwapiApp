package com.example.feature.data.remote

import android.util.Log
import com.example.core.network.toResult
import com.example.feature.data.local.FeatureDao
import com.example.feature.data.local.FeatureDatabase
import com.example.feature.data.local.toCharacter
import com.example.feature.data.local.toCharacterEntity
import com.example.feature.data.local.toFilm
import com.example.feature.data.local.toFilmEntity
import com.example.feature.data.local.toPlanet
import com.example.feature.data.local.toPlanetEntity
import com.example.feature.data.local.toStarship
import com.example.feature.data.local.toStarshipEntity
import com.example.feature.domain.entity.Character
import com.example.feature.domain.entity.Film
import com.example.feature.domain.entity.Planet
import com.example.feature.domain.entity.Starship
import com.example.feature.domain.repository.SwapiRepository
import com.example.feature.presentation.rvadapter.DataModel
import com.example.feature.presentation.rvadapter.RvAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
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
                    it.results.map {planetDto ->
                        val isFavorite = featureDao.isPlanetInFavorite(planetDto.name) > 0
                        planetDto.toPlanet(isFavorite)
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
                    it.results.map {starshipDto ->
                        val isFavorite = featureDao.isStarshipInFavorite(starshipDto.name) > 0
                        starshipDto.toStarship(isFavorite)
                    }
                }
            } catch (e: Exception) {
                return@withContext Result.failure(e)
            }

        }

    override fun getFavoriteCharacters(): Flow<List<Character>> {
        return featureDao.getFavoriteCharacters().map {
            it.map {
                it.toCharacter(true)
            }
        }
    }

    override fun getFavoritePlanets(): Flow<List<Planet>> {
        return featureDao.getFavoritePlanets().map {
            it.map {
                it.toPlanet(true)
            }
        }
    }

    override fun getFavoriteStarships(): Flow<List<Starship>> {
        return featureDao.getFavoriteStarships().map {
            it.map {
                it.toStarship(true)
            }
        }
    }

    override fun getFilms(): Flow<List<Film>> {
        return featureDao.getFilms().map {
            it.map {
                it.toFilm()
            }
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
            is DataModel.FilmInfo -> {}
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
            is DataModel.FilmInfo -> {}
        }
    }

}