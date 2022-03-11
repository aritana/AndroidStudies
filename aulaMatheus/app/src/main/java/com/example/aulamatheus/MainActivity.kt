package com.example.aulamatheus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import com.example.aulamatheus.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    companion object{
        const val NAME = "name"
        const val LASTNAME = "lastName"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  DataBindingUtil.setContentView(this,R.layout.activity_main)

    }
    fun onclickButton(view: View){
        var name = binding.maName.text.toString()
        var lastName = binding.maLastname.text.toString()

        Log.i("CadastroActivity", "$name $lastName")

        //set intent
        val intent = Intent(this, ExhibitionActivity::class.java)

        //sharing data
        intent.putExtra(NAME, name)
        intent.putExtra(LASTNAME, lastName)

        startActivity(intent)
    }

}