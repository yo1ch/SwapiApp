package com.example.feature.data.remote

import com.example.feature.data.remote.dto.CharacterDto
import com.example.feature.data.remote.dto.PlanetDto
import com.example.feature.data.remote.dto.StarshipDto
import com.example.feature.domain.entity.Character
import com.example.feature.domain.entity.Planet
import com.example.feature.domain.entity.Starship


fun CharacterDto.toCharacter(): Character =
    Character(
        name = name ?: "Unknown name",
        gender = gender ?: "Gender unknown",
        count = this.starships?.size ?: 0
    )

fun PlanetDto.toPlanet(): Planet =
    Planet(
        name = name,
        population = population,
        diameter = diameter,
    )

fun StarshipDto.toStarship(): Starship =
    Starship(
        name = name,
        manufacturer = manufacturer,
        passengers = passengers,
        model = model,
    )
