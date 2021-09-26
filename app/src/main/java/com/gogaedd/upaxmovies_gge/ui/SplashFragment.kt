package com.gogaedd.upaxmovies_gge.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.gogaedd.upaxmovies_gge.core.ConstantsApp
import com.gogaedd.upaxmovies_gge.databinding.FragmentSplashBinding
import com.gogaedd.upaxmovies_gge.viewmodel.SplashViewModel

class SplashFragment : Fragment() {
    lateinit var viewModel: SplashViewModel
    lateinit var binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(SplashViewModel::class.java)
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.getLvdStateload().observe(viewLifecycleOwner, observerStateload)
        viewModel.startLoadCat()


    }
    val observerStateload = Observer<Int>{
        if (it == ConstantsApp.STATE_LOAD.LOAD_OK){
            val action = SplashFragmentDirections.actionGoToMoviesMainFragment()
            findNavController().navigate(action)
        }
    }
}