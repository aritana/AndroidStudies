package com.aritana.network

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import okhttp3.*
import java.io.IOException

private var componentId: Int = 1

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        requestRetrofit()


    }

    private fun loadImage(url: String) {
        Glide.with(this)
            .load(url)
            .into(getImageView())
    }

    private fun getTextView(): TextView = findViewById<TextView>(R.id.heroNameTextView)

    private fun getImageView(): ImageView = findViewById<ImageView>(R.id.heroImageView)

    private fun heroDescription(): TextView = findViewById<Button>(R.id.heroDescriptionTextView)




    //Retrofit

    private fun requestRetrofit() {

        RetrofitNetwork().retrofitService.getHero(componentId)
            .enqueue(object : retrofit2.Callback<Hero> {
                override fun onResponse(
                    call: retrofit2.Call<Hero>,
                    response: retrofit2.Response<Hero>
                ) {
                    val hero = response.body()
                    updateTextView(hero?.name ?: "Empty")
                    if (hero != null) {
                        loadImage(hero.images.lg)
                        printDescription(hero.appearance)
                    }
                }

                override fun onFailure(call: retrofit2.Call<Hero>, t: Throwable) {
                    updateTextView("Fail!")
                }
            })

    }

    fun printDescription(appearance:Appearance){
       // Toast.makeText(this, value,Toast.LENGTH_SHORT).show()

        var textAppearance = "Gender: ${appearance.gender} "
        textAppearance += "\nRace:  ${appearance.race}"
        textAppearance += "\nHeight:  ${appearance.height[1]} "

        heroDescription().text = textAppearance
    }
    //OkHTTP
    private fun requestOkHttp() {

        var url = "https://akabab.github.io/superhero-api/api/all.json"
        val request: Request = Request.Builder()
            .url(url)
            .build()

        val okHttpClient = OkHttpClient().newBuilder().build()

        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                updateTextView("Fail!")
            }

            override fun onResponse(call: Call, response: Response) {
                updateTextView(response.body?.string() ?: "Empty")
            }
        })
    }

    private fun updateTextView(str: String) {

        runOnUiThread {
            getTextView().text = str
        }
    }

    fun nextImage(view: View) {
        componentId ++
        requestRetrofit()
    }

     fun previousImage(view: View) {

        if (componentId > 1) {
            componentId --
            requestRetrofit()
        }
    }
}