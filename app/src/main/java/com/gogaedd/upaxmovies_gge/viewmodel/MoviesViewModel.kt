package com.gogaedd.upaxmovies_gge.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.gogaedd.upaxmovies_gge.repository.MoviesRepository

class MoviesViewModel(application: Application): AndroidViewModel(application) {

    private val repository =  MoviesRepository(application)

    private val lvdPopularMovies = repository.getLvdPopularMovies()

    fun getLvdPopularMovies() = lvdPopularMovies


    fun startLoad(){
        repository.loadPopularMovies()
    }





}