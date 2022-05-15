package com.aritana.koinsiteteste

import org.koin.dsl.module

val helloModule = module {

    single { HelloMessageData() }

    single { HelloServiceImpl(get()) as HelloService }
}