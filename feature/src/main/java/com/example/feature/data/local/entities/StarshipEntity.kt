package com.example.feature.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "starship_table")
data class StarshipEntity(
    @PrimaryKey(autoGenerate = false)
    val name: String,
    val model: String,
    val manufacturer: String,
    val passengers: String,
    val films: List<String>,
)
