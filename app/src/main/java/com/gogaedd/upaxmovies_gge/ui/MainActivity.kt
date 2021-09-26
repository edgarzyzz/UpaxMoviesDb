package com.gogaedd.upaxmovies_gge.ui

import android.Manifest
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gogaedd.upaxmovies_gge.R
import androidx.core.app.ActivityCompat

import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.provider.Settings
import com.gogaedd.upaxmovies_gge.repository.MainRepository

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        requestPermissionsApp()
    }

    private fun requestPermissionsApp() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ),
            69
        )

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            69 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    MainRepository(application).startProccessStorePosition()

                } else {

                }
            }

        }
    }
}