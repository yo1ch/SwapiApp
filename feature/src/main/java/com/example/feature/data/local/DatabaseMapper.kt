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

fun CharacterEntity.toCharacter(): Character =
    Character(
        name = name,
        gender = gender,
        count = count,
        films = films,
    )

fun PlanetEntity.toPlanet(): Planet =
    Planet(
        name = name,
        population = population,
        diameter = diameter,
        films = films,
    )

fun StarshipEntity.toStarship(): Starship =
    Starship(
        name = name,
        manufacturer = manufacturer,
        passengers = passengers,
        model = model,
        films = films,
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