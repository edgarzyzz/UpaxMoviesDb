package com.gogaedd.upaxmovies_gge.repository

import android.annotation.SuppressLint
import android.app.Application
import android.net.Uri
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.OnProgressListener
import com.google.firebase.storage.UploadTask
import com.google.firebase.storage.ktx.storage

class ImagesRepository(val application: Application) {
    private val firebaseStorage = Firebase.storage
    private val storageReference = firebaseStorage.reference

    @SuppressLint("HardwareIds")
    val idDevice: String =
        Settings.Secure.getString(application.contentResolver, Settings.Secure.ANDROID_ID)

    var path = "images/"
    val root = storageReference.root
    val pathImgs = root.child(path)


    private val lvdImagesDevice = MutableLiveData<MutableList<String>>(mutableListOf())
    fun getLvdImagesDevice() = lvdImagesDevice


    fun setListImages(listImages: MutableList<String>) {
        lvdImagesDevice.postValue(listImages)

    }


    fun loadData() {

        pathImgs.listAll().addOnCompleteListener { taskListImg ->
            if (taskListImg.isSuccessful) {
                val result = taskListImg.result
                val listImagesNew = mutableListOf<String>()
                var i = 0
                result?.items!!.forEach { s ->
                    s.downloadUrl.addOnCompleteListener { taskUrl ->
                        if (taskUrl.isSuccessful) {
                            val uri = taskUrl.result
                            listImagesNew.add(uri.toString())
                            i++
                            if (i >= result.items.size) {
                                setListImages(listImagesNew)
                            }


                        }

                    }

                }

            }
        }
    }


    fun uploadImage(data: Uri) {
        val imagepath :String = "$path${System.currentTimeMillis()}.png"
        root.child(imagepath).putFile(data)
            .addOnSuccessListener {
                Toast.makeText(application, "Upload Successfully", Toast.LENGTH_SHORT)
                    .show()
                loadData()
            }
            .addOnFailureListener(OnFailureListener {
                Toast.makeText(application, "Upload Error", Toast.LENGTH_SHORT)
                    .show()
            })

    }

}