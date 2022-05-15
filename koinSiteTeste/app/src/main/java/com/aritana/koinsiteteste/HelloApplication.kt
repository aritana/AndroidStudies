package com.aritana.koinsiteteste

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

/**
 * HelloApplication - Application Class
 * use HelloService
 */
class HelloApplication : KoinComponent {

    // Inject HelloService
    val helloService by inject<HelloService>()

    // display our data
    fun sayHello() = println(helloService.hello())
}