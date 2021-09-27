package com.gogaedd.upaxmovies_gge.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gogaedd.upaxmovies_gge.databinding.ItemLocationBinding
import com.gogaedd.upaxmovies_gge.model.LocationApp

class LocationAdapter(): RecyclerView.Adapter<LocationAdapter.LocationViewHolder>() {
    var mListLocations = mutableListOf<LocationApp>()



    fun updateElements(listUpdated: MutableList<LocationApp>){
        mListLocations.clear()
        mListLocations.addAll(listUpdated)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =ItemLocationBinding.inflate(inflater,parent,false)
        return LocationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) =holder.bind(mListLocations[position])

    override fun getItemCount(): Int =mListLocations.size

    class LocationViewHolder(val binding: ItemLocationBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(location: LocationApp){
            binding.location=location
        }


    }
}