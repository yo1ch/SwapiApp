package com.example.feature.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "character_table")
data class CharacterEntity(
    @PrimaryKey(autoGenerate = false)
    val name: String,
    val gender: String,
    val count: Int,
    val films: List<String>
    )
