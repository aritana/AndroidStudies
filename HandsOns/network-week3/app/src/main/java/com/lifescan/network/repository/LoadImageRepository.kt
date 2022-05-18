package com.lifescan.network.repository

import com.lifescan.network.loadImage.LoadImageHeroResult
import com.lifescan.network.loadImage.LoadImageRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoadImageRepository(
    // Inject the Retrofit Service
) {
    fun getHeroImage(heroId: Int, onResponse: (String) -> Unit) {
        LoadImageRetrofit().retrofitService.getProperties(heroId).enqueue(object :
            Callback<LoadImageHeroResult> {

            override fun onResponse(
                call: Call<LoadImageHeroResult>,
                response: Response<LoadImageHeroResult>
            ) {
                response.body()?.images?.large?.let { onResponse(it) }
            }

            override fun onFailure(call: Call<LoadImageHeroResult>, t: Throwable) {
                onResponse("Failed to load image!")
            }
        })
    }
}