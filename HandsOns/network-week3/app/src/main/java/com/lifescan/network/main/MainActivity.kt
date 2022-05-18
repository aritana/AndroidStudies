package com.lifescan.network.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lifescan.network.R
import android.content.Intent
import android.view.View
import com.lifescan.network.loadImage.LoadImageActivity
import com.lifescan.network.loadJson.LoadJsonActivity
import com.lifescan.network.loadList.LoadListActivity
import com.lifescan.network.okHttp.OkHttpActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun loadOkHttpActivity(v: View) {
        val myIntent = Intent(this, OkHttpActivity::class.java)
        this.startActivity(myIntent)
    }

    fun loadJsonActivity(v: View) {
        val myIntent = Intent(this, LoadJsonActivity::class.java)
        this.startActivity(myIntent)
    }

    fun loadListActivity(v: View) {
        val myIntent = Intent(this, LoadListActivity::class.java)
        this.startActivity(myIntent)
    }

    fun loadImageActivity(v: View) {
        val myIntent = Intent(this, LoadImageActivity::class.java)
        this.startActivity(myIntent)
    }

}