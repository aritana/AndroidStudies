package com.lifescan.network.loadJson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.lifescan.network.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoadJsonActivity : AppCompatActivity() {

    private fun getTextView(): TextView = findViewById(R.id.loadJsonTextView)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_load_json)
        requestRetrofit()
    }

    private fun requestRetrofit() {
        updateTextView("Loading...")
        LoadJsonRetrofit().retrofitService.getProperties().enqueue(object: Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                updateTextView(response.body() ?: "Empty")
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                updateTextView(t.message.toString())
            }
        } )
    }

    private fun updateTextView(srtText: String) {
        runOnUiThread {
            getTextView().text = srtText
        }
    }
}