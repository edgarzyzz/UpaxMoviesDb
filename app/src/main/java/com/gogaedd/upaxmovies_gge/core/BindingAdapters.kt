package com.gogaedd.upaxmovies_gge.core

import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.gogaedd.upaxmovies_gge.adapters.ImageAdapter
import com.gogaedd.upaxmovies_gge.adapters.MoviewAdapter
import com.gogaedd.upaxmovies_gge.core.persistence.model.Movie

object BindingAdapters {


    @JvmStatic
    @BindingAdapter("visibilityMessageError")
    fun visibilityMessageError(viewError: View, state: Int){
        viewError.visibility = if (state== ConstantsApp.STATE_LOAD.LOAD_ERROR){
            View.VISIBLE
        }else{
            View.GONE
        }

    }

    @JvmStatic
    @BindingAdapter("visibilityLoader")
    fun visibilityLoader(viewLoader: View, state: Int){
        viewLoader.visibility = if (state== ConstantsApp.STATE_LOAD.START_LOADING){
            View.VISIBLE
        }else{

            View.GONE

        }

    }

    @JvmStatic
    @BindingAdapter("dataMovies")
    fun dataMovies(recyclerView: RecyclerView, listMovies : MutableList<Movie>){
        recyclerView.adapter?.let {adapterNotNull->
            if (adapterNotNull is MoviewAdapter){
                adapterNotNull.updateElements(listMovies)
            }
        }

    }

    @JvmStatic
    @BindingAdapter("dataImages")
    fun dataImages(recyclerView: RecyclerView, listImages: MutableList<String>){
        recyclerView.adapter?.let {adapterNotNull->
            if (adapterNotNull is ImageAdapter){
                adapterNotNull.updateElements(listImages)
            }
        }

    }



    @JvmStatic
    @BindingAdapter("parseAndDrawImageUrl")
    fun parseAndDrawImageUrl(imgview: ImageView, partlyUrl:String){
        val url ="https://image.tmdb.org/t/p/w500/$partlyUrl"


        if (partlyUrl.isNullOrEmpty()) return
        val options: RequestOptions = RequestOptions()
            .centerCrop()
            .placeholder(android.R.drawable.ic_menu_upload)
            .error(android.R.drawable.stat_notify_error)



        Glide.with(imgview.context).load(url).apply(options).into(imgview)
    }

    @JvmStatic
    @BindingAdapter("drawImageUrl")
    fun drawImageUrl(imgview: ImageView, url:String){
        if (url.isNullOrEmpty()) return
        val options: RequestOptions = RequestOptions()
            .centerCrop()
            .placeholder(android.R.drawable.ic_menu_upload)
            .error(android.R.drawable.stat_notify_error)



        Glide.with(imgview.context).load(url).apply(options).into(imgview)
    }
    @JvmStatic
    @BindingAdapter("drawRatingBy10")
    fun drawRatingBy10(rb: RatingBar, rate:Float){

        val fl = rate / 2
        rb.rating= fl

    }


}