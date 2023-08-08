package com.example.feature.presentation.rvadapter

import com.example.feature.domain.entity.Character
import com.example.feature.domain.entity.Planet
import com.example.feature.domain.entity.Starship

sealed class DataModel{

    class CharacterInfo(val character: Character): DataModel()

    class PlanetInfo(val planet: Planet): DataModel()

    class StarshipInfo(val starship: Starship): DataModel()

}