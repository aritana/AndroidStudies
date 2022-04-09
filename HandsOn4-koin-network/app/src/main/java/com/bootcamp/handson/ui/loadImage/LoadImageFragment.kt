package com.bootcamp.handson.ui.loadImage

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bootcamp.handson.R
import com.bootcamp.handson.databinding.FragmentLoadImageBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import org.koin.android.ext.android.inject

class LoadImageFragment : Fragment() {

    private var _binding: FragmentLoadImageBinding? = null
    private val binding
        get() = _binding!!

    private val viewModel: LoadImageViewModel by inject()

    // Create a fun to get a random HeroId
    private val heroId = (1..500).random()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoadImageBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestRetrofit()

        viewModel.getHeroImage(heroId)

        viewModel.image.observe(viewLifecycleOwner) {
            updateImageView(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun requestRetrofit() {
        updateImageView(R.drawable.ic_outline_image_search)
    }

    private fun updateImageView(imgUrl: String) {

        viewModel.isImageFailed.observe(viewLifecycleOwner) { isFailed ->
            if (!isFailed) {
                val imgView = binding.ivHero

                this.context?.let { context ->
                    Glide.with(context)
                        .load(imgUrl)
                        .apply(
                            RequestOptions()
                                .placeholder(R.drawable.ic_outline_image)
                                .error(R.drawable.ic_outline_image_not_supported)
                        ).into(imgView)
                }
            } else {
                Log.d("ImageFailedDebug", "Não tenho uma imagem para mostrar!")
                updateImageView(R.drawable.ic_outline_broken_image)
            }
        }
    }

    private fun updateImageView(resId: Int) {
        binding.ivHero.setImageResource(resId)
    }
}