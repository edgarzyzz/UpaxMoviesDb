package com.gogaedd.upaxmovies_gge.viewmodel

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import com.gogaedd.upaxmovies_gge.repository.ImagesRepository

class ImagesViewModel (application: Application) : AndroidViewModel(application) {
    private val repository = ImagesRepository(application)

    private val lvdImages = repository.getLvdImagesDevice()

    fun getLvdImages()=lvdImages


    fun startListenChangesCloud(){
        repository.loadData()

    }

    fun setImage(data: Uri) {
        repository.uploadImage(data)
    }



}