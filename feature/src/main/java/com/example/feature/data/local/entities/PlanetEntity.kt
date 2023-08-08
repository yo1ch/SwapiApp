package com.example.feature.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "planet_table")
data class PlanetEntity(
    @PrimaryKey(autoGenerate = false)
    val name: String,
    val diameter: String,
    val population: String,
    val films: List<String>
    )
