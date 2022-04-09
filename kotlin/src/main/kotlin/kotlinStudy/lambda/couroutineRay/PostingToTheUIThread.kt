package kotlinStudy.lambda.couroutineRay

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun main(){

    GlobalScope.launch{

        val bgThreadName = Thread.currentThread().name
        println("I'm job 1 in thread $bgThreadName")
        delay(200)

        GlobalScope.launch(Dispatchers.Main) {

            val uiThreadName = Thread.currentThread().name
            println("I'm Job 2 in thread $uiThreadName")

        }
    }
    Thread.sleep(1000)
}