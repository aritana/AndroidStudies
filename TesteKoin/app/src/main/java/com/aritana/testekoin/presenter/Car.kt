package com.aritana.testekoin.presenter

import android.util.Log
import com.aritana.testekoin.repository.Engine

class Car(private var engine:Engine) {

    fun turnOnCar(){
        Log.d("Car","${engine.start()} from $this}" )
    }
}