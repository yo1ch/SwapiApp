package com.example.feature.data.local

import com.example.feature.data.local.entities.CharacterEntity
import com.example.feature.data.local.entities.FilmEntity
import com.example.feature.data.local.entities.PlanetEntity
import com.example.feature.data.local.entities.StarshipEntity
import com.example.feature.data.remote.dto.FilmDto
import com.example.feature.domain.entity.Character
import com.example.feature.domain.entity.Film
import com.example.feature.domain.entity.Planet
import com.example.feature.domain.entity.Starship

fun CharacterEntity.toCharacter(isFavorite: Boolean = false): Character =
    Character(
        name = name,
        gender = gender,
        count = count,
        films = films,
        isFavorite = isFavorite,
    )

fun PlanetEntity.toPlanet(isFavorite: Boolean = false): Planet =
    Planet(
        name = name,
        population = population,
        diameter = diameter,
        films = films,
        isFavorite = isFavorite,
    )

fun StarshipEntity.toStarship(isFavorite: Boolean = false): Starship =
    Starship(
        name = name,
        manufacturer = manufacturer,
        passengers = passengers,
        model = model,
        films = films,
        isFavorite = isFavorite,
    )

fun Character.toCharacterEntity(): CharacterEntity =
    CharacterEntity(
        name = name,
        gender = gender,
        count = count,
        films = films,
    )

fun Planet.toPlanetEntity(): PlanetEntity =
    PlanetEntity(
        name = name,
        population = population,
        diameter = diameter,
        films = films,
    )

fun Starship.toStarshipEntity(): StarshipEntity =
    StarshipEntity(
        name = name,
        manufacturer = manufacturer,
        passengers = passengers,
        model = model,
        films = films,
    )

fun FilmDto.toFilmEntity(): FilmEntity =
    FilmEntity(
        name = title,
        director = director,
        producer = producer,
        url = url
    )

fun FilmEntity.toFilm(): Film =
    Film(
        name = name,
        director = director,
        producer = producer,
    )