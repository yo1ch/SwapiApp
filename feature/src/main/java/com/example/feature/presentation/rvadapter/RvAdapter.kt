package com.example.feature.presentation.rvadapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.feature.databinding.CharacterItemBinding
import com.example.feature.databinding.FilmItemBinding
import com.example.feature.databinding.PlanetItemBinding
import com.example.feature.databinding.StarshipItemBinding

class RvAdapter : ListAdapter<DataModel, BaseViewHolder<*>>(DiffUtilCallback) {

    var onClickListener: ((DataModel, Boolean) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return when (viewType) {
            ViewType.Character.ordinal -> {
                val characterBinding =
                    CharacterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                CharViewHolder(characterBinding)
            }

            ViewType.Planet.ordinal -> {
                val planetBinding =
                    PlanetItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                PlanetViewHolder(planetBinding)
            }

            ViewType.Starship.ordinal -> {
                val starshipBinding =
                    StarshipItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                StarshipViewHolder(starshipBinding)
            }

            ViewType.Film.ordinal -> {
                val filmBinding =
                    FilmItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                FilmViewHolder(filmBinding)
            }

            else -> throw RuntimeException("Unknown view type: $viewType")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is DataModel.PlanetInfo -> ViewType.Planet.ordinal
            is DataModel.CharacterInfo -> ViewType.Character.ordinal
            is DataModel.StarshipInfo -> ViewType.Starship.ordinal
            is DataModel.FilmInfo -> ViewType.Film.ordinal
            else -> -1
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is CharViewHolder -> {
                val item = getItem(position) as DataModel.CharacterInfo
                holder.binding.bookmark.isActivated = item.character.isFavorite
                holder.binding.name.text = item.character.name
                holder.binding.gender.text = buildString {
                    append("Gender: ")
                    append(item.character.gender)
                }
                holder.binding.aircraftCount.text = buildString {
                    append("Starships count: ")
                    append(item.character.count.toString())
                }
                holder.binding.root.setOnClickListener {
                    holder.binding.bookmark.isActivated = !holder.binding.bookmark.isActivated
                    onClickListener?.invoke(item, holder.binding.bookmark.isActivated)
                }
            }

            is PlanetViewHolder -> {
                val item = getItem(position) as DataModel.PlanetInfo
                holder.binding.bookmark.isActivated = item.planet.isFavorite
                holder.binding.name.text = item.planet.name
                holder.binding.population.text =
                    buildString {
                        append("Population: ")
                        append(item.planet.population)
                    }
                holder.binding.diameter.text = buildString {
                    append("Diameter: ")
                    append(item.planet.diameter)
                }
                holder.binding.root.setOnClickListener {
                    holder.binding.bookmark.isActivated = !holder.binding.bookmark.isActivated
                    onClickListener?.invoke(item, holder.binding.bookmark.isActivated)
                }
            }

            is StarshipViewHolder -> {
                val item = getItem(position) as DataModel.StarshipInfo
                holder.binding.bookmark.isActivated = item.starship.isFavorite
                holder.binding.name.text = item.starship.name
                holder.binding.manufacturer.text = buildString {
                    append("Manufacturer: ")
                    append(item.starship.manufacturer)
                }
                holder.binding.model.text = buildString {
                    append("Model: ")
                    append(item.starship.model)
                }
                holder.binding.passengers.text = buildString {
                    append("Passengers: ")
                    append(item.starship.passengers)
                }
                holder.binding.root.setOnClickListener {
                    holder.binding.bookmark.isActivated = !holder.binding.bookmark.isActivated
                    onClickListener?.invoke(item, holder.binding.bookmark.isActivated)
                }
            }

            is FilmViewHolder -> {
                val item = getItem(position) as DataModel.FilmInfo
                holder.binding.name.text = item.film.name
                holder.binding.producer.text = buildString {
                    append("Producer: ")
                    append(item.film.producer)
                }
                holder.binding.director.text = buildString {
                    append("Director: ")
                    append(item.film.director)
                }
            }
        }

    }

    enum class ViewType {
        Planet,
        Character,
        Starship,
        Film,
    }
}