package com.lifescan.network.loadImage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import com.lifescan.network.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class LoadImageActivity : AppCompatActivity() {

    private fun getImageView(): ImageView = findViewById(R.id.loadImageImageView)
    private val heroId = 432

    private val viewModel by lazy {
        ViewModelProvider(this).get(LoadImageViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_load_image)
        requestRetrofit()
    }

    override fun onResume() {
        super.onResume()

        viewModel.getHeroImage(heroId)

        viewModel.image.observe(this) {
            updateImageView(it)
        }
    }

    private fun requestRetrofit() {
        updateImageView(R.drawable.ic_outline_image_search)
    }

    private fun updateImageView(imgUrl: String) {
        val imgView = getImageView()

        Glide.with(this)
            .load(imgUrl)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.ic_outline_image)
                    .error(R.drawable.ic_outline_image_not_supported)
            ).into(imgView)
    }

    private fun updateImageView(resId: Int) {
        getImageView().setImageResource(resId)
    }
}