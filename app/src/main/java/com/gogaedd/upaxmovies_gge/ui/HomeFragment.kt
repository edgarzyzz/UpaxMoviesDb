package com.gogaedd.upaxmovies_gge.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.gogaedd.upaxmovies_gge.adapters.MoviewAdapter
import com.gogaedd.upaxmovies_gge.listeners.MovieListener
import com.gogaedd.upaxmovies_gge.core.persistence.model.Movie
import com.gogaedd.upaxmovies_gge.databinding.FragmentHomeBinding
import com.gogaedd.upaxmovies_gge.viewmodel.HomeViewModel

class HomeFragment : Fragment(), MovieListener {
    lateinit var mMoviePopulaAdapter: MoviewAdapter
    lateinit var mViewmodel : HomeViewModel
    lateinit var mBinding : FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        mViewmodel = ViewModelProvider(this). get(HomeViewModel::class.java)
        mBinding =  FragmentHomeBinding.inflate(inflater, container, false)

        mBinding.viewmodel =  mViewmodel
        mBinding.lifecycleOwner = viewLifecycleOwner

        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mMoviePopulaAdapter= MoviewAdapter()

        mMoviePopulaAdapter.setListener(this)


        mBinding.rvPopularmoviesFgmnth.apply {
            adapter = mMoviePopulaAdapter
            layoutManager = GridLayoutManager(requireContext(), 2 )


        }
        mViewmodel.startLoad()


    }

    override fun onClickMovie(moview: Movie) {
        val action = HomeFragmentDirections.actionGoToDetailMovieFragment(moview)
        findNavController().navigate(action)
    }


}