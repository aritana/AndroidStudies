package com.aritana.automatizando_testes_nativos_para_android_com_espresso.pageObjects.Login.robot

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.aritana.automatizando_testes_nativos_para_android_com_espresso.R
import com.aritana.automatizando_testes_nativos_para_android_com_espresso.pageObjects.Login.constants.ConstantsLogin.BTN_VOLTAR
import com.aritana.automatizando_testes_nativos_para_android_com_espresso.pageObjects.Login.constants.ConstantsLogin.EMAIL_SUCESSO
import com.aritana.automatizando_testes_nativos_para_android_com_espresso.pageObjects.Login.constants.ConstantsLogin.SENHA_SUCESSO

class RobotLogin{

    fun validarTituloNaTela(texto : String){

        onView(withText(texto))
            .check(matches(isDisplayed()))
    }

    fun escreverEmail(){
        onView(ViewMatchers.withId(CAMPO_EMAIL))
            .perform(ViewActions.typeText(EMAIL_SUCESSO))
    }

    fun escreverSenha(){

        onView(ViewMatchers.withId(R.id.editTextTextPassword))
            .perform(ViewActions.typeText(SENHA_SUCESSO))
    }

    fun fecharTeclado(){

        Espresso.closeSoftKeyboard() //fecha o teclado
    }

    fun clicarBotaoLogin(){
        onView(ViewMatchers.withId(R.id.btn_login1))
            .perform(ViewActions.click())
    }

    fun clicarBotaoVoltar(){
        onView(withText(BTN_VOLTAR))
            .perform(ViewActions.click())
    }

    companion object{
        private val CAMPO_EMAIL = R.id.txt_1_email
    }
}