package com.aritana.koinsiteteste

/**
 * Hello Service - interface
 */
interface HelloService {
    fun hello(): String
}


/**
 * Hello Service Impl
 * Will use HelloMessageData data
 */
class HelloServiceImpl(private val helloMessageData: HelloMessageData) : HelloService {

    override fun hello() = "Hey, ${helloMessageData.message}"
}