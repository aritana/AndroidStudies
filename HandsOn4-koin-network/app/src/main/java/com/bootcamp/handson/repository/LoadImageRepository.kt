package com.bootcamp.handson.repository

import com.bootcamp.handson.model.LoadImageHeroResult
import com.bootcamp.handson.network.LoadImageRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoadImageRepository(
    private val loadImageService: LoadImageRetrofit.LoadImageApiService
) {
    fun getHeroImage(heroId: Int, onResponse: (String) -> Unit) {
        loadImageService.getProperties(heroId).enqueue(object :
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