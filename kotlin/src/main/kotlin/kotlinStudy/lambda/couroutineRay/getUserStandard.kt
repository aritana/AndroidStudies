package kotlinStudy.lambda.couroutineRay

import kotlin.concurrent.thread


fun main() {
    getUserFromNetworkCallback("101") { user ->
        println(user)
    }
    println("main end")
}

data class User(val userId: String, val name: String)

fun getUserFromNetworkCallback(
    userId: String,
    onUserReady: (User) -> Unit) {
    thread {
        Thread.sleep(1000)

        val user = User(userId, "Filip")
        onUserReady(user)
    }
    println("end")
}
//