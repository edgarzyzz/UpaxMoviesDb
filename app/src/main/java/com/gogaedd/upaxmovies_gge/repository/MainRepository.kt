package com.gogaedd.upaxmovies_gge.repository

import android.Manifest
import android.annotation.SuppressLint
import android.app.Application
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.lifecycle.MutableLiveData
import com.gogaedd.upaxmovies_gge.LocationApp
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class MainRepository(val application: Application) : LocationListener {
    val dbFb = Firebase.firestore

    @SuppressLint("HardwareIds")
    val idDevice: String =
        Settings.Secure.getString(application.contentResolver, Settings.Secure.ANDROID_ID)

    var referenceLocations: CollectionReference =
        dbFb.collection("UpexDbAndroid").document(idDevice).collection("locations")

    var isInitiatedGps=false


    private val lvdLatitude = MutableLiveData<String>("0.0")
    private val lvdLongitude = MutableLiveData<String>("0.0")


    private fun getLat() = lvdLatitude.value
    private fun getLon() = lvdLongitude.value
    private fun setLat(latitude: String) {
        lvdLatitude.value = latitude
    }

    private fun setLongitude(longitude: String) {
        lvdLongitude.value = longitude
    }



    fun startProccessStorePosition() {
        startLocationManager()


    }

    @SuppressLint("MissingPermission")
    fun startLocationManager() {
        val locationManager =
            application.getSystemService(AppCompatActivity.LOCATION_SERVICE) as LocationManager
        val isActiveGps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)


        // getting network status
        val isNetworkEnabled = locationManager
            .isProviderEnabled(LocationManager.NETWORK_PROVIDER);



        if (isActiveGps && isNetworkEnabled) {
            //network provider is enabled
            locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                1000L,
                1f,
                this
            );
        }else{
            //Gps no activo ni red
        }






    }



    private fun startProcessStoreLocation() {
        storeLocation()
        relauchRunnable()

    }


    private fun storeLocation() {
        if (getLat() == "0.0") return

        val timestamp = System.currentTimeMillis().toString()

        val location2 = hashMapOf(
            "lat" to getLat(),
            "long" to getLon(),
            "timestamp" to timestamp
        )

        referenceLocations.document(timestamp).set(location2)
    }


    val runnable = Runnable {
        storeLocation()
        relauchRunnable()


    }

    private fun relauchRunnable() {
        Handler().postDelayed(runnable, 1800000)
//        Handler().postDelayed(runnable,10000)
    }

    override fun onLocationChanged(p0: Location) {
        setLat(p0.latitude.toString())
        setLongitude(p0.longitude.toString())
        if (!isInitiatedGps){
            startProcessStoreLocation()
            isInitiatedGps=true
        }
    }


}