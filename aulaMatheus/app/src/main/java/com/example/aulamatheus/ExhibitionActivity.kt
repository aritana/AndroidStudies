package com.example.aulamatheus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.example.aulamatheus.databinding.ActivityExhibitionBinding

class ExhibitionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExhibitionBinding

    companion object{
        const val NAME = "name"
        const val LASTNAME = "lastName"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_exhibition)

        //get shared data
        val intent: Intent = getIntent()
        val data = intent.extras

        if (data != null) {
            var name = data.get(NAME)
            var lastName = data.get(LASTNAME)
            Log.i("ExibicaoActivity", "$name $lastName")

            var message:String = getString(R.string.app_greeting,"$name $lastName")
            binding.aeName.text = message

        }

        binding.aeImage.setOnClickListener {

            binding.aeImage.setImageResource(R.drawable.hulk)

            var message:String = getString(R.string.app_greeting,"Hulk!")
            binding.aeName.text = message
        }
    }
}