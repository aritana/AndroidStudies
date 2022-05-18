package com.bootcamp.handson.repository

import com.bootcamp.handson.network.LoadJsonRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoadJsonRepository(
    private val loadJsonService: LoadJsonRetrofit.LoadJsonApiService
) {

    fun getJson(onResponse: (String) -> Unit) {
        loadJsonService.getProperties().enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                onResponse(response.body() ?: "Empty")
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                onResponse(t.message.toString())
            }
        })
    }

}