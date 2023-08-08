package com.example.feature.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.feature.data.local.entities.CharacterEntity
import com.example.feature.data.local.entities.FilmEntity
import com.example.feature.data.local.entities.PlanetEntity
import com.example.feature.data.local.entities.StarshipEntity
import com.example.feature.domain.entity.Character
import com.example.feature.domain.entity.Starship
import kotlinx.coroutines.flow.Flow


@Dao
interface FeatureDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavoriteCharacter(characterEntity: CharacterEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavoritePlanet(planetEntity: PlanetEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavoriteStarship(starshipEntity: StarshipEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveFilms(filmEntity: FilmEntity)

    @Delete
    suspend fun deleteFavoriteCharacter(characterEntity: CharacterEntity)

    @Delete
    suspend fun deleteFavoritePlanet(planetEntity: PlanetEntity)

    @Delete
    suspend fun deleteFavoriteStarship(starshipEntity: StarshipEntity)


    @Query("SELECT * FROM planet_table ORDER BY name ASC")
    fun getFavoritePlanets(): Flow<List<PlanetEntity>>

    @Query("SELECT * FROM starship_table ORDER BY name ASC")
    fun getFavoriteStarships(): Flow<List<StarshipEntity>>

    @Query("SELECT * FROM character_table ORDER BY name ASC")
    fun getFavoriteCharacters(): Flow<List<CharacterEntity>>

    @Query("SELECT * FROM film_table ORDER BY name ASC")
    fun getFilms(): Flow<List<FilmEntity>>



}