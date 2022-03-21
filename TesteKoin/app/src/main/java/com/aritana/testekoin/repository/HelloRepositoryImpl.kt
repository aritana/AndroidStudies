package com.aritana.testekoin.repository

class HelloRepositoryImpl:HelloRepository {
    override fun giveHello(): String {
       return "Hello Koin with Kotlin!"
    }
}