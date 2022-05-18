package kotlinStudy.lambda.couroutineRay

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun main() {

    val job1 = GlobalScope.launch(

        start = CoroutineStart.LAZY //the related code is going to be executed only when you actually needs it
    ) {
        delay(200)
        println("Pong")
        delay(200)
    }

    GlobalScope.launch {

        delay(200)
        println("1Ping")
        job1.join()// If a job A invokes join() on Job B, it means that the former won't be executed
        // until the latter has comer to completion.
        println("2Ping")
        delay(200)

    }
    Thread.sleep(1000)

}