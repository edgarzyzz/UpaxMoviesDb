package com.gogaedd.upaxmovies_gge.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.gogaedd.upaxmovies_gge.adapters.ImageAdapter
import com.gogaedd.upaxmovies_gge.databinding.FragmentImagesBinding
import com.gogaedd.upaxmovies_gge.viewmodel.ImagesViewModel
import kotlinx.android.synthetic.main.fragment_images.*
import java.io.IOException


class ImagesFragment : Fragment() {
    lateinit var adapterImages: ImageAdapter
    lateinit var viewmodel: ImagesViewModel
    lateinit var binding: FragmentImagesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewmodel = ViewModelProvider(this).get(ImagesViewModel::class.java)
        binding = FragmentImagesBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewmodel
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapterImages = ImageAdapter()





        rvImagesFgmntI.apply {
            adapter = adapterImages
            layoutManager = GridLayoutManager(requireContext(), 3)
        }

        viewmodel.startListenChangesCloud()
        fabAddImage.setOnClickListener {
            launchGallery()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 70 && resultCode == Activity.RESULT_OK) {
            if (data == null || data.data == null) {
                return
            }

            data?.data?.let {uriNotNull->
                viewmodel.setImage(uriNotNull)
            }

        }



    }

    private fun launchGallery() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 70)
    }


}

