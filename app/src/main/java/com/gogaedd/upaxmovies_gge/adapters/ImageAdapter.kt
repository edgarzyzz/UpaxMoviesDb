package com.gogaedd.upaxmovies_gge.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gogaedd.upaxmovies_gge.databinding.ItemImageBinding

class ImageAdapter(): RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    private val mListUrlImages = mutableListOf<String>()



    fun updateElements(currentList : MutableList<String>){
        mListUrlImages.clear()
        mListUrlImages.addAll(currentList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemImageBinding.inflate(inflater, parent,false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) =holder.bind(mListUrlImages[position])

    override fun getItemCount(): Int =mListUrlImages.size

    class ImageViewHolder(val binding:ItemImageBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(url: String){
            binding.urlImage =url

        }
    }

}