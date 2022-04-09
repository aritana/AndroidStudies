package kotlinStudy.lambda.couroutineRay

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun main(){

    GlobalScope.launch {

        println("Hello coroutine")
        delay(5000)//suspend the coroutine without block the thread
        println("Right back at ya")
    }
    Thread.sleep(6000)
}