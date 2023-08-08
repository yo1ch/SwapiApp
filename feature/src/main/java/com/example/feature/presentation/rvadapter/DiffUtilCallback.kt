package com.example.feature.presentation.rvadapter

import androidx.recyclerview.widget.DiffUtil

object DiffUtilCallback : DiffUtil.ItemCallback<DataModel>() {

    override fun areItemsTheSame(oldItem: DataModel, newItem: DataModel): Boolean {
        if (oldItem::class.java.name != newItem::class.java.name) return false
        return when (oldItem) {
            is DataModel.CharacterInfo -> {
                oldItem.character.name == (newItem as DataModel.CharacterInfo).character.name
            }
            is DataModel.PlanetInfo -> {
                oldItem.planet.name == (newItem as DataModel.PlanetInfo).planet.name
            }
            is DataModel.StarshipInfo -> {
                oldItem.starship.name == (newItem as DataModel.StarshipInfo).starship.name
            }
        }
    }

    override fun areContentsTheSame(oldItem: DataModel, newItem: DataModel): Boolean {
        if (oldItem::class.java.name != newItem::class.java.name) return false
        return when (oldItem) {
            is DataModel.CharacterInfo -> {
                oldItem.character == (newItem as DataModel.CharacterInfo).character
            }
            is DataModel.PlanetInfo -> {
                oldItem.planet == (newItem as DataModel.PlanetInfo).planet
            }
            is DataModel.StarshipInfo -> {
                oldItem.starship == (newItem as DataModel.StarshipInfo).starship
            }
        }
    }


}
