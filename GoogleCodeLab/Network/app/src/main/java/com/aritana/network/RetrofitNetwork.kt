package com.aritana.network

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

class RetrofitNetwork {

    private val BASE_URL:String = "https://akabab.github.io/superhero-api/api/"

    val retrofit = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    val retrofitService = retrofit.create(HeroJasonApi::class.java)


    interface  HeroJasonApi{
        @GET("all.json")
        fun getAllJason(): Call<String>

        @GET("id/{id}.json")
        fun getHero(@Path("id") heroId:Int): Call<Hero>
    }

}