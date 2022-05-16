package com.aritana.automatizando_testes_nativos_para_android_com_espresso.pageObjects.Login

import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.aritana.automatizando_testes_nativos_para_android_com_espresso.MainActivity
import com.aritana.automatizando_testes_nativos_para_android_com_espresso.pageObjects.Login.constants.ConstantsLogin.TEXTO_LOGADO
import com.aritana.automatizando_testes_nativos_para_android_com_espresso.pageObjects.Login.constants.ConstantsLogin.TITULO_APLICATIVO
import com.aritana.automatizando_testes_nativos_para_android_com_espresso.pageObjects.Login.robot.RobotLogin
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TesteLogin {

    private val robot = RobotLogin()

    @get:Rule //gerencia primeira activity aberta
    var myActivityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testeLoginModeloPage() {
        ActivityScenario.launch(MainActivity::class.java)
        robot.validarTituloNaTela(TITULO_APLICATIVO)
        robot.escreverEmail()
        robot.fecharTeclado()
        robot.escreverSenha()
        robot.fecharTeclado()
        robot.clicarBotaoLogin()
        robot.validarTituloNaTela(TEXTO_LOGADO)
        robot.clicarBotaoVoltar()
        robot.validarTituloNaTela(TITULO_APLICATIVO)
    }
}