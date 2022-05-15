package com.aritana.automatizando_testes_nativos_para_android_com_espresso

import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class TesteFormatoBasico {

    @get:Rule //gerencia primeira activity aberta
    var myActivityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testeLogin() {
        onView(withText("Aplicativo de Testes"))
            .check(matches(isDisplayed()))

        onView(withId(R.id.txt_1_email))
            .perform(typeText("ari@limao.com.br"))
        closeSoftKeyboard() //fecha o teclado

        onView(withId(R.id.editTextTextPassword))
            .perform(typeText("12345678"))
        closeSoftKeyboard() //fecha o teclado

        onView(withId(R.id.btn_login1))
            .perform(click())

        onView(withText("Logado com Sucesso"))
            .check(matches(isDisplayed()))

        onView(withText("Voltar"))
            .perform(click())

        onView(withText("Aplicativo de Testes"))
            .check(matches(isDisplayed()))
    }
}