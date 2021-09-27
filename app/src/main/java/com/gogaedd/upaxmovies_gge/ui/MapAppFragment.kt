package com.gogaedd.upaxmovies_gge.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.gogaedd.upaxmovies_gge.adapters.LocationAdapter
import com.gogaedd.upaxmovies_gge.databinding.FragmentMapAppBinding
import com.gogaedd.upaxmovies_gge.viewmodel.MapAppViewModel
import kotlinx.android.synthetic.main.fragment_map_app.*
import com.here.sdk.core.GeoCoordinates
import com.here.sdk.mapview.*

import com.here.sdk.mapview.MapScene.LoadSceneCallback
import android.R

import com.here.sdk.mapview.MapImageFactory

import com.here.sdk.mapview.MapImage





class MapAppFragment : Fragment() {
    lateinit var viewModel : MapAppViewModel
    lateinit var binding : FragmentMapAppBinding
    lateinit var adaperLocation : LocationAdapter
    lateinit var mMapView: MapView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(MapAppViewModel::class.java)
        binding = FragmentMapAppBinding.inflate(inflater, container, false)
        binding.viewModel= viewModel
        binding.lifecycleOwner=this


        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        adaperLocation = LocationAdapter()
        binding.rvListLoc.apply {
            adapter = adaperLocation
            layoutManager = LinearLayoutManager(requireContext())
        }

        viewModel.startloadLocationsDevice()

        mMapView = mapvApp
        mMapView.onCreate(savedInstanceState)
        loadMapScene()
        startMap()

    }
    private fun startMap() {

        mMapView.setOnReadyListener {
            Log.d("TAG", "startMap: ")
        }

    }


    private fun loadMapScene() {
        // Load a scene from the HERE SDK to render the map with a map scheme.
        mMapView.mapScene.loadScene(MapScheme.NORMAL_DAY) { mapError ->
            if (mapError == null) {
                val distanceInMeters = (1000 * 100).toDouble()
                mMapView.getCamera().lookAt(
                    GeoCoordinates(19.432275,-99.133930), distanceInMeters
                )
                startLoadMarkers()
            } else {
                Log.d("TAG", "Loading map failed: mapError: " + mapError.name)
            }
        }
    }

    private fun startLoadMarkers() {
        val mapImage = MapImageFactory.fromResource(requireContext().resources, R.drawable.ic_menu_myplaces)
        viewModel.getLocations()?.let{listLoc->
            listLoc.forEach{loc->
                val mapMarker = MapMarker(GeoCoordinates(loc.lat.toDouble(), loc.long.toDouble()), mapImage)
                mMapView.mapScene.addMapMarker(mapMarker)
            }

        }

    }

    override fun onPause() {
        super.onPause()
        mMapView!!.onPause()
    }


    override fun onResume() {
        super.onResume()
        mMapView!!.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        mMapView!!.onDestroy()
    }

}