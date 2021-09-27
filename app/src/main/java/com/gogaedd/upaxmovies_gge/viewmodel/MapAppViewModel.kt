package com.gogaedd.upaxmovies_gge.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.gogaedd.upaxmovies_gge.model.LocationApp
import com.gogaedd.upaxmovies_gge.repository.MapAppRepository

class MapAppViewModel(application: Application): AndroidViewModel(application) {
    val repository = MapAppRepository(application)
    val lvdListLocationsDevice = repository.getLvdListPositions()


    fun startloadLocationsDevice(){
        repository.getDataFirestore()
    }


    fun getLocations(): MutableList<LocationApp>? {
        return lvdListLocationsDevice.value
    }
}