package kotlinStudy.lambda.coroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

fun main() {
    (1..10000).forEach {

        GlobalScope.launch() {
            //launch: coroutine builder
            //https://flexiple.com/android/using-kotlin-coroutine-builders-in-android/
            //runblocking, launch, async

            //Global Scope: lifetime is bound to the lifecycle of the application


            val threadName = Thread.currentThread().name
            println("$it printed on thread ${threadName}")
        }
    }
    Thread.sleep(100)

}

/*
  You used launch() with the following signature:

  public fun CoroutineScope.launch(
  context: CoroutineContext = EmptyCoroutineContext,
  start: CoroutineStart = CoroutineStart.DEFAULT,
  block: suspend CoroutineScope.() -> Unit
): Job

CoroutineContext: Is a persistent dataset of contextual
information about the current coroutine.
This can contain objects like the job and Dispatcher
of the coroutine, here is empty, which point to whatever context the
specified coroutine scopes uses. You can create
custom contexts if you would like, but for the most part,
the existing one are sufficient.

CoroutineStart: Is the mode in which you can start a coroutine.
Options: Default: Immediately schedules a coroutine for execution
according to its context.
Lazy: Starts coroutine lazily.
Atomic: Same as DEFAULT but cannot be cancelled before it starts

Undispatched: Runs the coroutine untils its first suspension point.

Lambda: Which defines what's going to happen
when you launch the coroutine. The first two are optional.


*/

