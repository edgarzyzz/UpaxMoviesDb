package com.gogaedd.upaxmovies_gge.repository

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import com.gogaedd.upaxmovies_gge.utils.UtilsNet
import com.gogaedd.upaxmovies_gge.core.ConstantsApp
import com.gogaedd.upaxmovies_gge.core.persistence.db.AppDatabase
import com.gogaedd.upaxmovies_gge.core.persistence.net.NetListener
import com.gogaedd.upaxmovies_gge.core.persistence.net.NetMovies
import com.gogaedd.upaxmovies_gge.core.persistence.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class SplashRepository(val application: Application) : NetListener {

    private val db = AppDatabase.getDatabase(application)
    private val movieDao = db.movieDao()

    private val netMovies = NetMovies()

    private val lvdStateload = MutableLiveData<Int>(ConstantsApp.STATE_LOAD.START_LOADING)
    private val lvdMessageError = MutableLiveData<String>("")


    fun getLvdStateload() = lvdStateload
    fun getLvdMessageError() = lvdMessageError

    init {
        netMovies.setListener(this)
    }


    @RequiresApi(Build.VERSION_CODES.M)
    fun startLoadCat() {
        runBlocking(Dispatchers.IO) {
            setStateLoad(ConstantsApp.STATE_LOAD.START_LOADING)
            if (UtilsNet.isOnline(application)) {
                deleteInfoDb()
                getMostpopular()
                delay(1500)
            } else {
                if (existContentDb()) {
                    //todo:pasar a sig pantalla
                    lvdStateload.postValue(ConstantsApp.STATE_LOAD.LOAD_OK)
                } else {
                    //todo: mensaje de que se requiere inter
                    setMessageError("Se requiere internet para descargar informacion")
                    setStateLoad(ConstantsApp.STATE_LOAD.LOAD_ERROR)
                }

            }
        }


    }

    private fun setStateLoad(state: Int) {
        lvdStateload.postValue(state)
    }

    fun setMessageError(message: String) {
        lvdMessageError.postValue(message)
    }

    private fun deleteInfoDb() {
        movieDao.deleteAllMovies()

    }

    private fun existContentDb(): Boolean {

        val countTable = movieDao.getCountTable()

        return countTable > 0
    }


    private fun getMostpopular() {
        netMovies.sendRequestMostPopular()
    }

    override fun onResultOk(any: Any, code: Int) {
        when (code) {
            ConstantsApp.NET.WS_MOST_POPULAR_GET -> {
                if (any is MutableList<*>) {
                    val listMoviesPopular = any as MutableList<Movie>
                    runBlocking(Dispatchers.IO) {
                        saveMoviesInDb(listMoviesPopular)
                        //todo: Pasar a sig pantall
                        delay(3000)
                        lvdStateload.postValue(ConstantsApp.STATE_LOAD.LOAD_OK)

                    }
                }
            }
        }


    }


    override fun onResultError(meesageError: String, code: Int) {
        when (code) {
            ConstantsApp.NET.WS_MOST_POPULAR_GET -> {
                setMessageError("No se logro obtener popular movies")

            }

        }
        setStateLoad(ConstantsApp.STATE_LOAD.LOAD_ERROR)

    }


    private suspend fun saveMoviesInDb(
        listMovies: MutableList<Movie>
    ) {
        //Table movie
        movieDao.insertAllMovies(listMovies)


    }


}