fun main(args: Array<String>) {

    /*
        Lambdas arealso known as anonymous functions.
        Higher-order fucntion is a function that takes functions as parameters, or returns a function.
        Function types: (Int) -> String.
        take an Int and return a String.


     */
    println("oi Kotlin!")
    //lambdaBasics()
    //customSorting()
    // interatingOverCollectionsWithLambdas()
    //exercises()~~~~~~~~~~~~~~~~~
    // challenges()

    //    val x : Float = 35.0f
    //
    //    val y =  34.19 + x +35
    //
    //    println(y::class.java.typeName)
    // challenge2()
    challenge3()

}

fun lambdaBasics() {

    //declaration of a variable that can hold a lambda
    var multiplyLambda: (Int, Int) -> Int

    //assign
    multiplyLambda = { a: Int, b: Int ->
        Int
        a * b
    }

    var lambdaResult = multiplyLambda(4, 2)

    println(lambdaResult)

    val sumLambda: (Int, Int) -> Int

    sumLambda = { a: Int, b: Int ->
        Int
        a + b
    }

    var returnSumLambda = sumLambda(1, 5)

    println(returnSumLambda)

    //Shorthand syntax

    multiplyLambda = { a, b ->
        a * b
    }

    lambdaResult = multiplyLambda(1, 5)
    println(lambdaResult)

    var doubleLambda = { a: Int ->
        2 * a
    }

    //for a lambda that has only one parameter, you can shorten it
    //even further using it keyword.

    doubleLambda = { 2 * it }

    val doubleL: (Int) -> Int = { 2 * it }

    val square: (Int) -> Int = { it * it }

    val squareResult = square(2)
    val doubleLResult = doubleL(3)
    println(squareResult)
    println(doubleLResult)

    fun operateOnNumbers(a: Int, b: Int, operation: (Int, Int) -> Int): Int {

        val result = operation(a, b)
        println(result)
        return result

    }

    val addLambda = { a: Int, b: Int ->
        a + b
    }

    val result = operateOnNumbers(4, 2, operation = addLambda)
    println(result)

    fun addFunction(a: Int, b: Int) = a + b


    //The :: operator is the reference operator
    operateOnNumbers(4, 2, operation = ::addFunction) // 6


    operateOnNumbers(4, 2, operation = { a: Int, b: Int ->
        a + b
    })


    println(operateOnNumbers(4, 2, operation = Int::plus))

    //It can only be done wehen the lambda is the final argument passed to a function
    // In this case, you can move the lambda outside of the function call

    operateOnNumbers(4, 2) { a, b ->
        a + b
    }//trailing lambda syntax

    //lambdas are not required to do these things.
    //A lambda will always return the value of its last expression
    //So here is how you define a lambda that takes no parameters
    //and returns only the Unit object:

    var unitLambda: () -> Unit = {
        println("Kotling Apprentice is awesome!")
    }

    unitLambda()

    //not return a value

    var nothingLambda: () -> Nothing = { throw java.lang.NullPointerException() }
    //Since an exception is thrown, the lambda does not actually return a value.

    //Capturing from the enclosing scope
    //They can access the variables and constants from within their own scope.

    var counter = 0
    val incrementCounter = {
        counter += 1
    }

    fun countingLambda(): () -> Int {

        var counter = 0
        val incrementCounter: () -> Int = {
            counter += 1
            counter
        }
        return incrementCounter
    }

    val counter1 = countingLambda()
    val counter2 = countingLambda()

    println(counter1())
    println(counter1())
    println(counter2())
    println(counter2())


}

fun customSorting() {

    val names = arrayOf("ZZZZ", "BB", "CCCC", "EEEEE")
    println(names.sorted())

    //sorted by the length of the string with longer strings
    //coming first. The minus sign causes the sort to be
    //descending by length
    val namesByLength = names.sortedWith(compareBy {
        -it.length
    })

    println(namesByLength)

    val namesByLengthAsc = names.sortedWith(compareBy {
        it.length
    })

    println(namesByLengthAsc)

}

fun interatingOverCollectionsWithLambdas() {

    val values = listOf<Int>(1, 2, 3, 4, 5, 6)

    values.forEach {
        println("$it: ${it * it}")
    }

    var prices = listOf<Double>(1.5, 10.0, 4.99, 2.30, 8.19)
    val largePrices = prices.filter {
        it > 5.0
    }

    println(largePrices)


    val salePrices = prices.map {
        it * 0.9
    }

    println(salePrices)


    val userInput = listOf("0", "11", "haha", "42")
    val numbers = userInput.map {
        it.toIntOrNull()
    }

    println(numbers)

    val userInput2 = listOf("0", "11", "haha", "42")
    val numbers2 = userInput.mapNotNull {
        it.toIntOrNull()
    }

    println(numbers2)

    //(1.5, 10.0, 4.99, 2.30, 8.19)
    val sum = prices.fold(0.0) { a, b ->
        a + b
    }//uses the 0.0 as the inital value a,

    println(sum)

    val sum2 = prices.reduce { a, b ->
        a + b
    }//uses the 0.0 as the inital of the list as a,
    println(sum2)

    val stock = mapOf<Double, Int>(1.5 to 5, 10.0 to 2, 4.99 to 20, 2.30 to 5)
    var stockSum = 0.0

    val stock2 = mapOf(1.5 to 5, 10.0 to 2, 4.99 to 20, 2.30 to 5, 8.19 to 30)
    stock2.forEach {
        stockSum += it.key * it.value
    }

    println(stockSum)

}

fun exercises() {

    val nameList = mutableListOf<String>("Rob", "Maria", "João", "Ilidiana")
    val concatenation = nameList.reduce {

            a, b ->
        a + b

    }
    println(concatenation)

    val fourCharacter = nameList.filter {
        it.length > 4
    }


    println(fourCharacter)

    val fourCharacter2 = nameList.filter {
        it.length <= 4
    }.reduce {

            a, b ->
        a + b
    }

    println(fourCharacter2)

    val namesAndAges = mutableMapOf<String, Int>(
        "Maria" to 23,
        "João" to 25,
        "Augusto" to 32,
        "Fernando" to 15,
        "Lia" to 13,
        "Alberto" to 9
    )

    val namesAndAgensUnder18 = namesAndAges.filter {
        it.value < 18
    }

    println(namesAndAgensUnder18)

    val namesAndAgensOver18 = namesAndAges.filter {
        it.value >= 18
    }

    println(namesAndAgensOver18)

    val values = namesAndAges.map {
        it.value
    }

    println(values)

}

fun challenges() {

    var lambda = {
        println("Kotling Apprentice great book!")
    }

    fun repeatTask(times: Int, task: () -> Unit) {

        for (i in 1..times) {
            task()
        }

    }
    repeatTask(2, lambda)


//    fun mathSum(length: Int, series: (Int) -> Int) -> Int{
//
//    }

}

fun challenge2() {

    var squareNumbers = { position: Int ->
        Int

        val series: List<Int> = (1..100).map { it * it }
        series.get(position - 1)

    }

    fun fibonacci(number: Int): Int {

        if (number == 1) {
            return 1
        } else
            if (number == 2) {

                return 1
            } else {

                return fibonacci(number - 1) + fibonacci(number - 2)
            }
    }


    var fibonnaci = { number: Int ->
        Int

        if (number == 1) {
            1

        } else if (number == 2) {
            1
        } else {
            fibonacci(number - 1) + fibonacci(number - 2)
        }
    }

    //  println(squareNumbers(10))

    fun mathSum(length: Int, series: (Int) -> Int): Int {

        var sum = 0

        for (i in 1..length) {

            sum += series(i)

        }
        return sum

    }

    println("Square Numbers: ${mathSum(10, squareNumbers)}")
    println("Fibonacci Numbers: ${mathSum(10, fibonnaci)}")

}

fun challenge3() {

    val appRatings = mapOf(
        "Calendar Pro" to arrayOf(1, 5, 5, 4, 2, 1, 5, 4),
        "The Messenger" to arrayOf(5, 4, 2, 5, 4, 1, 1, 2),
        "Socialise" to arrayOf(2, 1, 2, 2, 1, 2, 4, 2)
    )
    //map
    val averageRatings = mutableMapOf<String, Double>()

    appRatings.forEach {

        val sum: Int = it.value.reduce { a, b ->
            a + b
        }

        val average: Double = sum.toDouble() / it.value.size
        averageRatings[it.key] = average
    }


    val filter = averageRatings.filter {
        it.value > 3
    }.map{
        it.key
    }

    println(filter)


}


