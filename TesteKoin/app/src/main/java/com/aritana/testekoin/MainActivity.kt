package com.aritana.testekoin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.aritana.testekoin.presenter.Car
import com.aritana.testekoin.presenter.MySimplePresenter
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.textView)
//
//        val firstPresenter:MySimplePresenter by inject()
//
//        firstPresenter.sayHello()
//
//        Log.d("MainActivity","Car Starting" )

        val car : Car by inject()

        car.turnOnCar()


        textView.text = "Olá Mundo"
    }
}