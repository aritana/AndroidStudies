package kotlinStudy.lambda.coroutines

import kotlinx.coroutines.*

val a = 10
val b = 20

fun main() {
    println("Gone to calculate sum of a & b")

    GlobalScope.launch {
        val result = async {
            calculateSum()
        }
        println("Sum of a & b is: ${result.await()}")
    }
    println("Carry on with some other task while the coroutine is waiting for a result...")
    runBlocking {
        delay(3000L) // keeping jvm alive till calculateSum is finished
    }
}

suspend fun calculateSum(): Int {
    delay(2000L) // simulate long running task
    return a + b
}