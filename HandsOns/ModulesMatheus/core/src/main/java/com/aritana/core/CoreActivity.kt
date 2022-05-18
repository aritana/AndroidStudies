package com.aritana.core

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class CoreActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_core)


        /*   // setContentView(R.layout.activity_core) //comenta
           val textView = TextView(this)
           textView.text="Hello Custom View Core"
           setContentView(textView)*/

/*

    Android draws the layout hierarchy in three stages:
    Measuring stage: each view must measure itself.
    Layout stage: each ViewGroup finds the right position for its children on the screen by using the child size and also by following the layout rules.
    Drawing stage: after measuring and positioning all of the views, each view happily draws itself. :]

 */



    }
}