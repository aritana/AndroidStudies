import java.lang.Math.sqrt

fun main(args: Array<String>) {

    //doNothingForever()
    //ranges()
    // forLoop()
    //repeat()
    // printMultipleOfFive(value = 5)//named argument
    // printMultipleOfFiveWithDefaultValue()//default value is 1
    //val value = printMultipleOfFiveWithReturn(5)
    //println(value)

    //val value = singleExpression(10)
    //println(value)

    //sobrecarga: o compilador nao examina o retorno. Logo só analiza
    //nome, numero do método, tipos e ordens de seus parametros.
    //var intValuePlusOne = getValue(5);
    //var StringValue = getValue("Hi");
    //println(intValuePlusOne)
    //println(StringValue)

    //method reference operator ::
//    var function = ::add
//    println(function(5,3))
//
//    function = ::subtract //is compatible with the add..
//    println(function(3,2))

    //passing function to other fucntion with the method referece operator ::
    // printResult(::add,2,1)
    // infiniteLoop()
    //   var numberPrime =  isNumberPrime(8893)
    //   println(numberPrime)

    //  println(fibonacci(10))

     nullabilityFunction()
    val times = divideIfWhole(3, 5) ?: 0


    println("It divides $times times")

}


//Nothing:
fun doNothingForever(): Nothing {
    while (true) {
        println("1")
    }
}

fun ranges() {

    val closedRange = 0..5
    val halfOpenRange = 0 until 5
    val decreasingRange = 5 downTo 0

    for (item in halfOpenRange) {
        println(item)
    }

}

fun forLoop() {

    for (i in 0..7) {
        //  println("--------------------------------------")

        for (j in 0..7) {
            if (i % 2 != 0) {
                //        print(" ${i * j} |")

            } else {
                //          print("  |")
            }

        }
        //     println()
    }
//----
    for (i in 0..7) {
        // println("--------------------------------------")

        for (j in 0..7) {
            if (j < i) {
                //   print(" ${i * j} |")
            }

        }
        //println()
    }
    //

    var sum = 0
    rowLoop@ for (row in 0 until 8) {
        columnLoop@ for (column in 0 until 8) {
            if (row == column) {
                continue@rowLoop
            }
            sum += row * column
            print(sum)
        }
        println()
    }

}


fun repeat() {

    var i = 0
    repeat(10) {
        println(i++)
    }
}

fun printMultipleOfFive(value: Int) {
    println("$value * 5 = ${value * 5}")
}

fun printMultipleOfFiveWithDefaultValue(value: Int = 1): Int {
    println("$value * 5 = ${value * 5}")
    return value * 5
}

fun printMultipleOfFiveWithReturn(value: Int): Int {
    return value * 5
}

fun singleExpression(value: Int) = value * 5

fun getValue(value: Int): Int {
    return value + 1
}

fun getValue(value: String): String {
    return "The value is $value"
}

fun add(a: Int, b: Int): Int {
    return a + b
}

fun subtract(a: Int, b: Int): Int {
    return a - b
}

fun printResult(function: (Int, Int) -> Int, a: Int, b: Int) {

    val result = function(a, b)
    print(result)
}

//function that nevers return

fun infiniteLoop(): Nothing {
    while (true) {

    }
}

fun isNumberPrime(number: Int): Boolean {

    var count = 0

    if (number < 0) return false

    for (i in 2..sqrt(number.toDouble()).toInt()) {

        if (number % i == 0) {
            count++
            break
        }

    }
    return count == 0
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

//nullability
fun nullabilityFunction() {

    var errorCode: Int? //nullable Int, can contain Int or null,
    // can never ber inferred from intialization values
    errorCode = 100   //filling the box with value
    errorCode = null  // emptying the box


    var parseInt = "10".toIntOrNull() //nullable
    //parseInt="dog"

    var result: Int? = 30
    //println(result + 1) //error

    var authorName: String? = "Jose Amaranto"
    var authorAge: Int? = 24

    //to remove these nullables from the box we have to
    //use not-null assertion operator !!
    val ageAfterBirthday = authorAge!! + 1 //type Int
    println("After their next birthday, author will be $ageAfterBirthday")

    var nonNullableAuthor: String
    var nullableAuthor: String?

    if (authorName != null) {
        nonNullableAuthor = authorName
    } else {
        nullableAuthor = authorName
    }

    //exercicse

    var myFavoriteSong: String? = null

    var nonNullableFavoriteSong: String
    var nullableFavoriteSong: String? = null

    //smart casts
    if (myFavoriteSong != null) {

        nonNullableFavoriteSong = myFavoriteSong
    } else {
        nullableFavoriteSong = myFavoriteSong
        println("I don’t have a favorite song")
    }


    //SafeCall
    var a = nullableFavoriteSong?.length
    println(a)
    println(a)

    //chained
    var b = nullableFavoriteSong?.length?.plus(5)
    println(b)

    //a and b are nullnable types

    //The let() function

    authorName = "Aritana"
    authorName?.let {
        nonNullableAuthor = authorName //author name inside a let function becomes non-nullable
        //and you can acess its properties
        println(" TEste: $nonNullableAuthor")
    }

    //Elvis Operator

    var nullableInt: Int? = 10
    var mustHaveResult = nullableInt ?: 0


}

fun nullabilityFunctionChallenge() {

    var name: String? = "Ray"
    // var age: Int = null
    val distance: Float = 26.7f //the compiler infers the Double type.
    var middleName: String? = null


}

fun divideIfWhole(numberDividend: Int, numberDivisor: Int): Int? {

    var remainder: Int? = null
    var quotient: Int
    remainder = numberDividend % numberDivisor
    quotient = numberDividend / numberDivisor
    if (remainder == 0) {
        return quotient
    }
    return null
}