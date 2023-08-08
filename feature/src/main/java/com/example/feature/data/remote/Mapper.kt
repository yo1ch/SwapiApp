package com.example.feature.data.remote

import com.example.feature.data.remote.dto.CharacterDto
import com.example.feature.data.remote.dto.PlanetDto
import com.example.feature.data.remote.dto.StarshipDto
import com.example.feature.domain.entity.Character
import com.example.feature.domain.entity.Planet
import com.example.feature.domain.entity.Starship


fun CharacterDto.toCharacter(isFavorite: Boolean): Character =
    Character(
        name = name,
        gender = gender,
        count = starships.size,
        films = films,
        isFavorite = isFavorite
    )

fun PlanetDto.toPlanet(): Planet =
    Planet(
        name = name,
        population = population,
        diameter = diameter,
        films = films,
    )

fun StarshipDto.toStarship(): Starship =
    Starship(
        name = name,
        manufacturer = manufacturer,
        passengers = passengers,
        model = model,
        films = films,
    )


