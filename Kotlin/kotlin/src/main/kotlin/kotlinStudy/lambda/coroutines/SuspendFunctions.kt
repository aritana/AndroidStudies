package kotlinStudy.lambda.coroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//https://flexiple.com/android/using-kotlin-coroutine-builders-in-android/

fun main(){

    println("Starting main function..")

    GlobalScope.launch {

        println(doSomething())
    }
    runBlocking {

        delay(4000L)
    }

}

suspend fun doSomething():String{
    delay(3000L)
    return "Did something that was 3 seconds long"
}