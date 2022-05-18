package com.example.myfirstapphandson

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

//extend Activity
//ir em manifest: registrar a activity, linhas 12-18
//para rodar no seu celular, ative modo dev e dupuracao
class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toast.makeText(this, "Bem vindo(a) ao meu primeiro app", Toast.LENGTH_SHORT).show()

        //setup da view
        //view criada em res/layout
        setContentView(R.layout.activity_main)
        val clearButon = findViewById<Button>(R.id.clearButton)//botao clear
        clearButon.setOnClickListener { //coleta de dados de caixas de texto
            val textBox = findViewById<EditText>(R.id.textCx)
            val text = textBox.text.toString()
            Log.i("MainActivity", "OnCreate: $text")
        }//CTR SHIF ESPACO para mostrar implementacao
    }

}