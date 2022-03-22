package com.aritana.modulesmatheus

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.aritana.featuremodulea.FeatureModuleAActitivity
import com.aritana.featuremoduleb.FeatureModuleBActitivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var buttonA = findViewById<Button>(R.id.buttonA)
        buttonA.setOnClickListener {

            goToFeatureA()
        }

        var buttonB = findViewById<Button>(R.id.buttonB)
        buttonB.setOnClickListener {

            goToFeatureB()
        }
    }

    fun goToFeatureA() {


        val intent = Intent(this, FeatureModuleAActitivity::class.java)
        startActivity(intent)
    }

    fun goToFeatureB() {

        val intent = Intent(this, FeatureModuleBActitivity::class.java)
        startActivity(intent)
    }
}