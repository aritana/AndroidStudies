package com.phro.sharedpreferences

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.phro.sharedpreferences.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var view: View
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var firstName: String
    private lateinit var lastName: String
    private lateinit var countDownTimer: CountDownTimer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        sharedPreferences =
            getSharedPreferences(getString(R.string.sharedPreferenceName), Context.MODE_PRIVATE)

    }

    override fun onPause() {
        super.onPause()
        deleteToken()
    }

    override fun onStart() {
        super.onStart()

        val defaultToken = "emptyToken"
        val actualOrDefaultToken =
            sharedPreferences.getString(getString(R.string.token), defaultToken)
        if (actualOrDefaultToken != defaultToken) {
            goToHome()

        }
    }

    private fun saveToken() {
        sharedPreferences.edit()
            .putString(getString(R.string.token), generateToken())
            .apply()
    }

    private fun generateToken(): String {
        val prime = 31
        var result = 1

        result = prime * result + firstName.hashCode()
        result = prime * result + lastName.hashCode()

        return result.toString()
    }

    private fun saveNameAndLastName() {
        sharedPreferences.edit()
            .putString(getString(R.string.first_name), firstName)
            .putString(getString(R.string.last_name), lastName)
            .apply()
    }

    // Crie uma lógica para que o token seja apagado depois de x tempo.
    fun deleteToken() {

        countDownTimer = object : CountDownTimer(10000, 1000) {
            override fun onTick(millisUntilFinished: Long) {

                Log.i("SignUpActivity",(millisUntilFinished.toInt() / 1000).toString())
            }
            override fun onFinish() {
                var editor: SharedPreferences.Editor = sharedPreferences.edit();
                editor.remove(getString(R.string.token))
                editor.apply();
            }
        }
        countDownTimer.start()

    }

    fun onclickButton(view: View) {

        firstName = binding.firstName.text.toString()
        lastName = binding.lastName.text.toString()

        saveToken()
        saveNameAndLastName()

        goToHome()

    }

    fun goToHome() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }
}