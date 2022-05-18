package com.lifescan.handson3.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CreateRetrofit {

    val retrofitBuilder by lazy {
        Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val retrofitService by lazy {
        retrofitBuilder.create(ApiService::class.java)
    }

    companion object {
        const val BASE_URL = "https://api.github.com"
    }
}