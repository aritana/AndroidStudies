package com.lifescan.handson3.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/users/{user}")
    fun getUserDetails(@Path("user") user:String): Call<ResponseResult>

}