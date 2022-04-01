package kotlinStudy.lambda.section2

import java.util.*

fun main(args: Array<String>) {

    println("oi Kotlin!")
    mutableLists()

//args.forEach { arg->
//    println(arg)
//}

    arrays()
// lists()
// mutableLists()
//    slice()
//    checkAnElement()
//    contains()
//    appendElements()
//    insertElements()
//    removingELements()
//    updatingElements()
//    interatingThroughAList()
//    listChallenge()
    //     listChange5()
}

fun arrays() {
//ordered collection of values of the same type.
    //arrayOf -> Array<Int>
    //Array
    //intArrayOf primitive ->intArray


    val evenNumbers = arrayOf(2, 4, 6, 8)

    val evenNumbers2 = arrayOf<Int>(1, 2)

    val asc = Array(5) { i ->
        i * i
    }
    println(asc.forEach {
            arg ->println(arg)
    })

    val fiveFives = Array(5, { 5 })//five fives

    val vowels = arrayOf("a", "b", "c")
    vowels[0] = "s"

    //primitive types are compiled to a Java array of type int[]
    val oddNumbers = intArrayOf(1, 3, 5, 7)
    val decimalNumbers = doubleArrayOf(1.1, 2.2)

    val zeros = DoubleArray(4)

    //You can convert between the boxed and primitve arrays using functions
    val otherOddNumbers = arrayOf(1, 35, 7).toIntArray()
    //otherOddNumbers[5]=10 //can't be being dynamically-seized
    for (i in otherOddNumbers) {
        println(i)
    }
}

fun lists() {
//List type in Kotlin is an interface
//that has concrete realizations in types such as ArrayList, LinkedList and others.
//lists have the additional feature of being dynamically-sized.

    var innerPlanets = listOf("Mercury", "Venus", "Earth", "Mars")
    //List<String>

    val subscribers: List<String> = listOf()//empty list
    val subscribers2: List<String> = listOf<String>()//empty list

    val innerPlanetsArrayList = arrayListOf<String>("Mercury", "Venus", "Earth", "Mars")



    for (i in innerPlanetsArrayList) {
        println(i)
    }
}

fun mutableLists() {

    val outerPlanets = mutableListOf<String>("Jupiter", "Saturn", "Uranus", "Neptune")
    val exoPlanets = mutableListOf<String>()

    outerPlanets.add("Teste");
    println(outerPlanets)

    val players = mutableListOf<String>("Alice", "João Bumba", "Abice")

    print(players.isEmpty())

    if (players.size < 2) {

        println("We need at least two players!")
    } else {
        println("Let's start!")
    }

    var currentPlayer = players.first()
    println(currentPlayer)

    val minPlayer = players.minOrNull()
    minPlayer.let {
        println("$minPlayer will start")
    }

//    val firstPlayer = players[0]
//    println("First player is $firstPlayer")

    val firstPlayer = players.get(0)
    println("First player is $firstPlayer")


}

fun slice() {
    //fetch more than a single value from an array or list

    val players = mutableListOf<String>("Alice", "João Bumba", "Maranhão", "Pé de pano")

    val upcomingPlayerSlice = players.slice(1..2)
    println(upcomingPlayerSlice.joinToString())


}

fun checkAnElement() {
    //check if there's at least one occurrence of a specific element by
    //using in operator
    val players = mutableListOf<String>("Alice", "João Bumba", "Maranhão", "Pé de pano")

    println("Alice" !in players)
}

fun contains() {

    val players = mutableListOf<String>("Alice", "João Bumba", "Maranhão", "Pé de pano")
    println(players.slice(1..2).contains("Maranhão"))

}

fun appendElements() {

    val players = mutableListOf<String>("Alice", "João Bumba", "Maranhão", "Pé de pano")
    players.add("Eli")

    players += "Regina"


    var array = arrayOf(1, 2, 3)
    array += 4 // a new array is created and its reference is set in the array variable

    for (i in array) println(i)

}

fun insertElements() {

    val players = mutableListOf<String>("Alice", "João Bumba", "Maranhão")
    players.add(1, "Bia")
    for (i in players) println(i)
}

fun removingELements() {
    val players = mutableListOf<String>("Alice", "João Bumba", "Maranhão")

    var wasPlayerRemoved = players.remove("Maranhão")

    val indexOfJoao = players.indexOf("João Bumba")
    var playerRemoved = players.removeAt(indexOfJoao)

    for (i in players) println(i)
    println("Removed $wasPlayerRemoved")
    println("Player removed $playerRemoved")

}

fun updatingElements() {

    val players = mutableListOf<String>("Alice", "João Bumba", "Maranhão")
    val indexOfJoao = players.indexOf("João Bumba")
    players[indexOfJoao] = "Bumba"
//    for (i in players) println(i)

    players[2] = "Bahia"
    players.set(1, "Amanda")

    players.sort()
    //for (i in players) println(i)

    //you can update the elements of an aray using indexing syntax
    val arrayOfInts = arrayOf(1, 2, 3)
    arrayOfInts[0] = 4
    println(arrayOfInts.joinToString())
}

fun interatingThroughAList() {

    val players = mutableListOf<String>("Alice", "João Bumba", "Maranhão")


    for ((index, player) in players.withIndex()) {
        println("${index + 1}.$player")
    }

    fun sumOfElements(list: List<Int>): Int {
        var sum = 0
        for (number in list) {
            sum += number
        }
        return sum
    }

    val numbers = mutableListOf<Int>(1, 2, 3)
    println(sumOfElements(numbers))

}

fun nullability() {

    var nullableList: List<Int>? = listOf(1, 2, 3)//the individual element are of type Int
    //and cannot be null, but the list itself can be null

    nullableList = null

    var listOfNullables: List<Int?> = listOf(1, 2, null)

    var nullableListOfNullables: List<Int?>? =
        listOf(1, 2, null) //Both the list and its elements can be null


}

fun listChallenge() {

    // Write a function that removes the first occurrence of a given integer from a list of integers
    // This is the signature of the function:

    fun removeOne(item: Int, list: List<Int>): List<Int> {

        val index = list.indexOf(item)
        var newList = mutableListOf<Int>()
        newList = list.toMutableList()
        newList.removeAt(index)

        return newList

    }


    var listOfIntegers = mutableListOf<Int>(1, 2, 3, 4, 2, 29, 0)
    var newListOfIntegers = removeOne(2, listOfIntegers)

    // println(newListOfIntegers.joinToString ())


    //Write a function that removes all occurrences of a given integer
    //from a list of integers. This is the signature of the function

    fun remove(item: Int, list: List<Int>): List<Int> {

        var newList = mutableListOf<Int>()
        var listOfItemsToBeRemoved = listOf(item)
        newList = list.toMutableList()
        newList.removeAll(listOfItemsToBeRemoved)

        return newList
    }

    //original array in reverse
    fun reverse(array: Array<Int>): Array<Int> {


        var limite = array.size - 1
        var newArray = Array<Int>(array.size, { 0 })

        // 2, 4, 6, 8
        for (i in 0..limite) {
            newArray[i] = array[limite - i]
            //println("i $i")
            // println(array[limite - i])
        }
        return newArray

    }

    newListOfIntegers = remove(2, listOfIntegers)
    // println(newListOfIntegers.joinToString ())
    // println(newListOfIntegers.reversed())

    val evenNumbers = arrayOf(2, 4, 6, 8) //Array<Int>

    //reverse(evenNumbers)
    println(reverse(evenNumbers).joinToString())


}

fun listChange5() {

    val random = Random()

    fun rand(from: Int, to: Int): Int {
        return random.nextInt(to - from) + from
    }

    fun randomized(array: Array<Int>): Array<Int> {

        var newArray = Array<Int>(array.size, { 0 })
        newArray = array.clone()

        for (i in newArray.indices) {

            var currentValue = newArray.get(i)
            var newPosition = rand(0, array.size)

            newArray
            newArray[i] = newArray[newPosition]
            newArray[newPosition] = currentValue
        }

        return newArray

    }

    var oddNumbers = arrayOf(1, 3, 5, 7, 15, 2, 9, 17, 0)
    var newArray = randomized(oddNumbers)
    println(newArray.joinToString())


}