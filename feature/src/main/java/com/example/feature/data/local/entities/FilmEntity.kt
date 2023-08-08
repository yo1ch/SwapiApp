package com.example.feature.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "film_table")
data class FilmEntity(
    @PrimaryKey(autoGenerate = false)
    val name: String,
    val director: String,
    val producer: String,
    val url: String
)
