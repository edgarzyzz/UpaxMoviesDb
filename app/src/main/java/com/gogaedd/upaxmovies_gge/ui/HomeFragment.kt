package com.gogaedd.upaxmovies_gge.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.gogaedd.upaxmovies_gge.R
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cvMoviesHome.setOnClickListener{
            val action = HomeFragmentDirections.actionGoToMoviesMainFragment()
            findNavController().navigate(action)
        }

        cvMapHome.setOnClickListener{
            val action = HomeFragmentDirections.actionGoToMapAppFragment()
            findNavController().navigate(action)
        }

        cvMyCloudHome.setOnClickListener{
            val action = HomeFragmentDirections.actionGoToImagesFragment()
            findNavController().navigate(action)
        }
    }

}