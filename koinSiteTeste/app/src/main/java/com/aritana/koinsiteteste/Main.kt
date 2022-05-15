package com.aritana.koinsiteteste

import org.koin.core.context.startKoin

fun main(vararg args: String) {

    startKoin {
        // use Koin logger
        printLogger()
        // declare modules
        modules(helloModule)
    }

    HelloApplication().sayHello()
}
