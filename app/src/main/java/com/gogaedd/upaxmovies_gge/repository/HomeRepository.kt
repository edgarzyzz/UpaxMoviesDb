package com.gogaedd.upaxmovies_gge.repository

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.gogaedd.upaxmovies_gge.core.persistence.db.AppDatabase
import com.gogaedd.upaxmovies_gge.core.persistence.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

class HomeRepository(application: Application) {
    private val db = AppDatabase.getDatabase(application)
    private val movieDao = db.movieDao()

    private val lvdPopularMovies = MutableLiveData<MutableList<Movie>>(mutableListOf())

    fun getLvdPopularMovies() = lvdPopularMovies


    fun loadPopularMovies(){
        runBlocking(Dispatchers.IO) {
            val moviesById = movieDao.getAllMovies()
            lvdPopularMovies.postValue(moviesById)

        }
    }

}