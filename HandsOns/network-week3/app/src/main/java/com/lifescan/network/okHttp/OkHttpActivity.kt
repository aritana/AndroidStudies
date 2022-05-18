package com.lifescan.network.okHttp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.lifescan.network.R
import okhttp3.*
import okhttp3.Request.Builder
import java.io.IOException


class OkHttpActivity : AppCompatActivity() {

    private fun getTextView(): TextView = findViewById(R.id.okHttpTextView)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ok_http)
        requestOkHttp()
    }

    private fun requestOkHttp() {
        updateTextView("Loading ...")
        val okHttpClient = OkHttpClient()
        val url = "https://akabab.github.io/superhero-api/api/all.json"

        val request: Request = Builder()
            .url(url)
            .build()

        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    updateTextView(response.body?.string() ?: "Empty")
                } else {
                    updateTextView("Fail!")
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                updateTextView("Error!")
            }

        })
    }

    private fun updateTextView(strText: String) {
        runOnUiThread {
            getTextView().text = strText
        }
    }
}