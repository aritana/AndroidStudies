package kotlinStudy.lambda.coroutines

import kotlinx.coroutines.*

//https://flexiple.com/android/using-kotlin-coroutine-builders-in-android/

fun main(){

    println("Starting main function..")

    GlobalScope.async {

        println(doSomething2())
    }
    runBlocking {

        delay(3000L)
    }

}

suspend fun doSomething2(){
    delay(2000L)
    print("this is bad example for using async!!!")
}