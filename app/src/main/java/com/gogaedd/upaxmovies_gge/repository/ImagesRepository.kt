package com.gogaedd.upaxmovies_gge.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class ImagesRepository(application: Application) {

    private val lvdImagesDevice = MutableLiveData<MutableList<String>>(mutableListOf())
    fun getLvdImagesDevice()= lvdImagesDevice


    fun setListImages(listImages: MutableList<String>){
        lvdImagesDevice.postValue(listImages)

    }



    fun loadData(){
        val firebaseStorage = Firebase.storage
        val storageReference = firebaseStorage.reference
        val root = storageReference.root
        root.listAll().addOnCompleteListener {taskListImg->
            if (taskListImg.isSuccessful) {
                val result = taskListImg.result
                val listImagesNew = mutableListOf<String>()
                var i=0
                result?.items!!.forEach {s->
                    s.downloadUrl.addOnCompleteListener {taskUrl->
                        if (taskUrl.isSuccessful){
                            val uri= taskUrl.result
                            listImagesNew.add(uri.toString())
                            i++
                            if (i>= result.items.size){
                                setListImages(listImagesNew)
                            }


                        }

                    }

                }

            }
        }
    }

}