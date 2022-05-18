package com.lifescan.network.loadImage

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

class LoadImageRetrofit {

    companion object {
        private const val BASE_URL = "https://akabab.github.io/superhero-api/api/"
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }

    val retrofitService: LoadImageApiService by lazy {
        retrofit.create(LoadImageApiService::class.java)
    }

    interface LoadImageApiService {
        @GET("id/{id}.json")
        fun getProperties(@Path("id") heroId: Int): Call<LoadImageHeroResult>
    }
}
