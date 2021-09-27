package com.gogaedd.upaxmovies_gge.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.gogaedd.upaxmovies_gge.R
import com.gogaedd.upaxmovies_gge.adapters.LocationAdapter
import com.gogaedd.upaxmovies_gge.databinding.FragmentMapAppBinding
import com.gogaedd.upaxmovies_gge.viewmodel.MapAppViewModel

class MapAppFragment : Fragment() {
    lateinit var viewModel : MapAppViewModel
    lateinit var binding : FragmentMapAppBinding
    lateinit var adaperLocation : LocationAdapter


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

    }


}