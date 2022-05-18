package com.lifescan.handson3

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.lifescan.handson3.adapter.MyRecyclerViewAdapter
import com.lifescan.handson3.api.ApiService
import com.lifescan.handson3.api.CreateRetrofit
import com.lifescan.handson3.api.ResponseResult
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = MyRecyclerViewAdapter(emptyList())
        recyclerView.adapter = adapter

        getUser(adapter)
    }

    private fun getUser(adapter: MyRecyclerViewAdapter) {
        val list: MutableList<ResponseResult> = mutableListOf()

        // Build Retrofit and get service
        var service: ApiService = CreateRetrofit().retrofitService

        val userList =
            listOf(
                "vitor-risso",
                "leonardoamurca",
                "diego3g",
                "Alvarenga-Dev",
                "rafaballerini",
                "omariosouto",
                "maykbrito",
                "torvalds"
            )

        val response = userList.map {
            service.getUserDetails(it)
        }

        response.forEach {
            it.enqueue(object : retrofit2.Callback<ResponseResult> {
                override fun onResponse(
                    call: Call<ResponseResult>,
                    response: Response<ResponseResult>,
                ) {
                    list.add(response.body()!!)
                    adapter.update(list)
                    Log.d("LOG", "deu bom , response  = ${response.body()}")
                }

                override fun onFailure(call: Call<ResponseResult>, t: Throwable) {
                    Log.d("LOG", "deu ruim, call  = $call")
                }
            })
        }
    }
}