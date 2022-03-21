package com.aritana.testekoin.presenter

import android.util.Log
import com.aritana.testekoin.repository.HelloRepository

class MySimplePresenter(val repo:HelloRepository) {

    fun sayHello(){

        Log.d("MySimplePresenter","Car Starting ${repo.giveHello()} from $this}" )
    }

}