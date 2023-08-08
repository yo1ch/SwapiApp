package com.example.feature.presentation.rvadapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.feature.databinding.CharacterItemBinding
import com.example.feature.databinding.PlanetItemBinding
import com.example.feature.databinding.StarshipItemBinding

class RvAdapter: ListAdapter<DataModel, BaseViewHolder<*>>(DiffUtilCallback) {

    var onClickListener: ((DataModel, Boolean) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return when(viewType){
            ViewType.Character.ordinal -> {
                val characterBinding = CharacterItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
                CharViewHolder(characterBinding)
            }
            ViewType.Planet.ordinal -> {
                val planetBinding = PlanetItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
                PlanetViewHolder(planetBinding)
            }
            ViewType.Starship.ordinal -> {
                val starshipBinding = StarshipItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
                StarshipViewHolder(starshipBinding)
            }
            else -> throw RuntimeException("Unknown view type: $viewType")
        }
    }
    override fun getItemViewType(position: Int): Int {
        return when(getItem(position)){
            is DataModel.PlanetInfo -> ViewType.Planet.ordinal
            is DataModel.CharacterInfo -> ViewType.Character.ordinal
            is DataModel.StarshipInfo -> ViewType.Starship.ordinal

            else -> -1
        }
    }
    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is CharViewHolder -> {
                val item = getItem(position) as DataModel.CharacterInfo
                holder.binding.name.text = item.character.name
                holder.binding.gender.text = item.character.gender
                holder.binding.aircraftCount.text = item.character.count.toString()
                holder.binding.root.setOnClickListener {
                    holder.binding.bookmark.isActivated = !holder.binding.bookmark.isActivated
                    onClickListener?.invoke(item, holder.binding.bookmark.isActivated)
                }
            }
            is PlanetViewHolder ->{
                val item = getItem(position) as DataModel.PlanetInfo
                holder.binding.name.text = item.planet.name
                holder.binding.population.text = item.planet.population
                holder.binding.diameter.text = item.planet.diameter
                holder.binding.root.setOnClickListener {
                    holder.binding.bookmark.isActivated = !holder.binding.bookmark.isActivated
                    onClickListener?.invoke(item, holder.binding.bookmark.isActivated)
                }
            }
            is StarshipViewHolder ->{
                val item = getItem(position) as DataModel.StarshipInfo
                holder.binding.name.text = item.starship.name
                holder.binding.manufacturer.text = item.starship.manufacturer
                holder.binding.model.text = item.starship.model
                holder.binding.passengers.text = item.starship.passengers
                holder.binding.root.setOnClickListener {
                    holder.binding.bookmark.isActivated = !holder.binding.bookmark.isActivated
                    onClickListener?.invoke(item, holder.binding.bookmark.isActivated)
                }
            }
        }

    }
    enum class ViewType{
        Planet,
        Character,
        Starship,
    }
}