package com.aritana.featuremodulea

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aritana.core.CoreActivity

class FeatureModuleAActitivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feature_module_a)


        var intent =  Intent(this, CoreActivity::class.java)
        startActivity(intent)
    }
}