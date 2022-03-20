package com.lifescan.network.loadList

import retrofit2.Call
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import retrofit2.http.GET

class LoadListRetrofit {
    companion object {
        private const val BASE_URL = "https://akabab.github.io/superhero-api/api/"
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }

    val retrofitService: LoadListApi by lazy {
        retrofit.create(LoadListApi::class.java)
    }

    interface LoadListApi {
        @GET("all.json")
        fun getSuperHeroes(): Call<List<LoadListResult>>
    }


}