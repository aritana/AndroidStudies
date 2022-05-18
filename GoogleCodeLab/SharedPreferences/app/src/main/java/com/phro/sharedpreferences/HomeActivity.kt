package com.phro.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.phro.sharedpreferences.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var view: View
    private lateinit var sharedPreferences : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        view = binding.root.rootView
        setContentView(view)

        sharedPreferences = getSharedPreferences(getString(R.string.sharedPreferenceName), Context.MODE_PRIVATE)
    }

    override fun onStart() {
        super.onStart()

        val defaultValueName = "emptyName"
        val defaultValueLastName = "emptyLastName"
        val actualOrDefaultFirstName =  sharedPreferences.getString( getString(R.string.first_name),defaultValueName)
        val actualOrDefaultLastName =  sharedPreferences.getString(getString(R.string.last_name),defaultValueLastName)

        binding.nameLastName.text = getString(R.string.nameLastName, "$actualOrDefaultFirstName $actualOrDefaultLastName")

    }
}