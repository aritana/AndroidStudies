package kotlinStudy.lambda.section3.methods

import kotlin.math.PI
import kotlin.math.sqrt

val months = arrayOf(
    "January", "February", "March",
    "April", "May", "June",
    "July", "August", "September",
    "October", "November", "December"
)

class SimpleDate(var month: String) {

    val monthsUntilWinterBreak: Int
        get() {
            return months.indexOf("December") - months.indexOf(month)
        }
}

class MyMath() {
    companion object {

        fun factorial(number: Int): Int {
            return (1..number).fold(1) { a, b -> a * b }
        }

        fun trianguleNumber(number: Int): Int {
            return (1..number).reduce { a, b -> a + b }
        }
    }
}


fun main(args: Array<String>) {

    //refresher()
    //objectMethods()
    //extensionMethods()
    //companionObjectExtensions()
    challenges()

}

fun refresher() {

    val date1 = SimpleDate("January")
    println(date1.monthsUntilWinterBreak)
}

fun objectMethods() {

    println(MyMath.factorial(6))
    println(MyMath.trianguleNumber(6))

}

fun extensionMethods() {

    fun SimpleDate.monthsUntilSummerBreak(): Int {

        val monthIndex = months.indexOf(month)
        return if (monthIndex in
            0..months.indexOf("June")
        ) {
            months.indexOf("June") - months.indexOf(month)
        } else if (monthIndex in months.indexOf("June")..months.indexOf("August")) {
            0
        } else {
            months.indexOf("June") + (12 - months.indexOf(month))
        }

    }

    val date = SimpleDate("December")

    println(date.monthsUntilSummerBreak()) // > 6

    fun Int.abs(): Int {
        return if (this < 0) -this else this
    }
    println(4.abs())    // > 4
    println((-4).abs()) // > 4

}

fun companionObjectExtensions() {
    //adding methods in te companion object of a class
    fun MyMath.Companion.primeFactors(value: Int): List<Int> {

        var remainingValue = value
        var testFactor = 2
        val primes = mutableListOf<Int>()

        while (testFactor * testFactor <= remainingValue) {
            if (remainingValue % testFactor == 0) {
                primes.add(testFactor)
                remainingValue /= testFactor
            } else {
                testFactor += 1
            }
        }
        if (remainingValue > 1) {
            primes.add(remainingValue)
        }
        return primes
    }
    println(MyMath.primeFactors(81))

}

//Challenges
class Circle(var radius: Double = 0.0) {
    var area: Double = 0.0
        get() {
            return PI * radius * radius
        }

    fun grow(factor: Double) {

        this.radius = radius * sqrt(factor)
    }
}

class SimpleDate2(var month: String, var day: Int = 0) {
    fun advance() {
        day += 1
    }
}


fun challenges() {

    var circle1 = Circle(2.0)
    println(circle1.area)

    circle1.grow(2.0)

    println(circle1.area)


    var date = SimpleDate2(month = "December", day = 31)
    date.advance()
    println(date.month) // December; should be January!
    println(date.day) // 32; should be 1!

}

