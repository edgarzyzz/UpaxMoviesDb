package com.gogaedd.upaxmovies_gge.repository

import android.annotation.SuppressLint
import android.app.Application
import android.provider.Settings
import androidx.lifecycle.MutableLiveData
import com.gogaedd.upaxmovies_gge.model.LocationApp
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MapAppRepository(application: Application) {
    @SuppressLint("HardwareIds")
    val idDevice: String = Settings.Secure.getString(application.contentResolver, Settings.Secure.ANDROID_ID)

    private val dbFb = Firebase.firestore
    private var referenceLocations: CollectionReference = dbFb.collection("UpexDbAndroid").document(idDevice).collection("locations")
    private val lvdListPositions = MutableLiveData<MutableList<LocationApp>>(mutableListOf())

    fun getLvdListPositions()=lvdListPositions


    fun getDataFirestore(){
        referenceLocations.addSnapshotListener{snapshot,e->
            if (e!=null){
                return@addSnapshotListener
            }
            if (snapshot!=null && !snapshot.isEmpty){

                val mutableListOf = mutableListOf<LocationApp>()
                for (doc in snapshot){
                    val toObject = doc.toObject(LocationApp::class.java)
                    mutableListOf.add(toObject)
                }
                lvdListPositions.postValue(mutableListOf)


            }

        }
    }


}