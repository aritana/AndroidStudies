package com.lifescan.network.loadJson

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

class LoadJsonRetrofit {

    companion object {
        private const val BASE_URL = "https://akabab.github.io/superhero-api/api/"
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .addConverterFactory(ScalarsConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }

    val retrofitService: LoadJsonApiService by lazy {
        retrofit.create(LoadJsonApiService::class.java)
    }

    interface LoadJsonApiService {
        @GET("all.json")
        fun getProperties(): Call<String>
    }
}